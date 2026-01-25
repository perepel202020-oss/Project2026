package com.perepel.feature.rules.presentation.screen

import com.perepel.domain.model.Rule
import com.perepel.feature.rules.presentation.viewmodel.RulesViewModel
import kotlinx.coroutines.runBlocking

class RulesScreen(
    private val viewModel: RulesViewModel
) {

    fun display() {
        println("=".repeat(70))
        println("📋 ЭКРАН ПРАВИЛ")
        println("=".repeat(70))

        runBlocking {
            viewModel.loadRules()

            // Получаем правила напрямую из ViewModel
            val rules = viewModel.rules.value

            if (rules.isEmpty()) {
                println("📭 Правила не найдены")
            } else {
                displayRules(rules)
            }
        }
    }

    private fun displayRules(rules: List<Rule>) {
        println("\n🎯 ВАШИ ПРАВИЛА НА ДЕНЬ:")
        println("-".repeat(70))

        rules.forEachIndexed { index, rule ->
            val number = (index + 1).toString().padStart(2, ' ')
            println("$number. [ ] ${rule.title}")
            println("     ${rule.description}")
            println()
        }

        println("-".repeat(70))
        println("Всего правил: ${rules.size}")
        println("\n💡 КОМАНДЫ:")
        println("  complete <номер> - отметить правило выполненным")
        println("  refresh - обновить список")
        println("  exit - выйти из экрана правил")
    }

    fun handleCommand(command: String) {
        when {
            command.startsWith("complete ") -> {
                val ruleNumber = command.removePrefix("complete ").toIntOrNull()

                runBlocking {
                    val rules = viewModel.rules.value

                    if (ruleNumber != null && ruleNumber in 1..rules.size) {
                        val ruleId = rules[ruleNumber - 1].id
                        viewModel.toggleRuleCompletion(ruleId)
                        println("✅ Правило $ruleNumber '${rules[ruleNumber - 1].title}' отмечено выполненным!")
                    } else {
                        println("❌ Неверный номер правила. Доступно правил: ${rules.size}")
                    }
                }
            }

            command == "refresh" -> {
                runBlocking {
                    println("🔄 Обновление правил...")
                    viewModel.loadRules()
                    println("✅ Правила обновлены!")
                }
            }

            command == "exit" -> {
                println("👋 Выход из экрана правил")
            }

            command == "help" -> {
                println("📖 ДОСТУПНЫЕ КОМАНДЫ:")
                println("  complete <номер> - отметить правило выполненным")
                println("  refresh - обновить список правил")
                println("  help - показать справку")
                println("  exit - выйти из экрана правил")
            }

            else -> {
                println("❓ Неизвестная команда: '$command'")
                println("   Введите 'help' для списка команд")
            }
        }
    }
}
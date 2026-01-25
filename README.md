# 🚀 Project 2026 - Modular Android Architecture

![Android](https://img.shields.io/badge/Android-100%25-green)
![Kotlin](https://img.shields.io/badge/Kotlin-100%25-blueviolet)
![Architecture](https://img.shields.io/badge/Clean_Architecture-✅-orange)
![Koin](https://img.shields.io/badge/Koin_DI-3.5.0-yellow)

Professional modular architecture Android application for habit tracking.

## 📱 Features

### ✅ 5 Working Features:
1. **📋 Rules Management** - 7 daily habits, tracking, completion
2. **📅 Calendar Tracking** - 365-day visual progress
3. **📊 Statistics & Analytics** - Charts, percentages, recommendations
4. **🔔 Notifications** - Motivational reminders, scheduler
5. **⚙️ App Settings** - Dark/Light theme

### 🏗️ Architecture (10 Modules):
Структура проекта Project-2026
text
project-2026/
├── :app                   # Точка входа + Koin DI
├── :core                  # Утилиты и общие функции
├── :core-ui               # UI компоненты
├── :domain                # Бизнес-логика
├── :data                  # Слой данных
├── :feature-rules         # Управление правилами
├── :feature-tracking      # Ежедневное отслеживание
├── :feature-statistics    # Аналитика
├── :feature-notifications # Уведомления
└── :feature-settings      # Настройки темы
Описание модулей
:app
Точка входа приложения

Настройка DI через Koin

Конфигурация приложения

Главная активность/композабл

:core
Общие утилиты и хелперы

Расширения (extensions)

Константы

Общие модели данных

:core-ui
Переиспользуемые UI компоненты

Тема приложения

Стили и ресурсы

Кастомные View/Composables

:domain
Бизнес-логика приложения

Use cases (интеракторы)

Репозитории (интерфейсы)

Доменные модели

:data
Слой данных

Реализация репозиториев

Локальная БД (Room)

Удаленные источники (Retrofit)

Data mappers

:feature-rules
Управление правилами

Создание/редактирование правил

Хранение и валидация правил

:feature-tracking
Ежедневное отслеживание

Запись и мониторинг данных

История трекинга

:feature-statistics
Аналитика и статистика

Графики и отчеты

Визуализация данных

:feature-notifications
Система уведомлений

Напоминания

Планировщик уведомлений

:feature-settings
Настройки приложения

Управление темой (темная/светлая)

Настройки пользователя

Конфигурация приложения

Зависимости между модулями
text
:app → (:core, :core-ui, все feature-модули)
feature-модули → (:domain, :core, :core-ui)
:domain → (:core)
:data → (:core, :domain)
:core-ui → (:core)
Такая модульная структура обеспечивает:

Чистую архитектуру

Разделение ответственности

Повторное использование кода

Упрощенное тестирование

Масштабируемость

## 🛠️ Technology Stack

- **Language:** 100% Kotlin
- **Architecture:** Clean Architecture + MVVM
- **DI:** Koin (8 modules)
- **Async:** Coroutines + StateFlow
- **Modularity:** 10 independent modules
- **Testing:** Ready for unit/integration tests

## 🚀 Quick Start

### Prerequisites:
- Android Studio Giraffe/2022+
- JDK 17+
- Git

### Steps:
```bash
# 1. Clone repository
git clone https://github.com/perepel202020-oss/Project2026.git

# 2. Open in Android Studio
# 3. Wait for Gradle sync
# 4. Run Main.kt: app/src/main/kotlin/com/perepel/app/Main.kt
package com.perepel.data.mapper

import com.perepel.data.local.entity.RuleEntity
import com.perepel.domain.model.Rule

object RuleMapper {
    fun toDomain(entity: RuleEntity): Rule {
        return Rule(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            order = entity.order
        )
    }

    fun toEntity(domain: Rule): RuleEntity {
        return RuleEntity(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            order = domain.order
        )
    }
}
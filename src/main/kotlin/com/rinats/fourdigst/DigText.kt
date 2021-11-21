package com.rinats.fourdigst

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "dig_text")
data class DigText(
    @Id
    val digits: String,
    val text: String,
    @Column(name = "expire")
    val expire: Date
)
package com.rinats.fourdigst

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DigTextRepository : JpaRepository<DigText, String>
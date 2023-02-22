package com.suromo.database.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/22
 * desc   :
 */
@Entity(
    tableName = "mark_six"
)
data class MarkSix(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "longperiod") val longperiod: Int,
    @ColumnInfo(name = "period") val period: String,
    @ColumnInfo(name = "numbers") val numbers:String,
    @ColumnInfo(name = "sx") val sx:String,
    @ColumnInfo(name = "wx") val wx:String,
    @ColumnInfo(name = "date") val date:String)
package aliabbas.com.scalablecodebaseapp.database.db.tables

import aliabbas.com.scalablecodebaseapp.database.db.converters.ListToStringTypeConverterBarData
import aliabbas.com.scalablecodebaseapp.model.repository_commit.BarData
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * Created By Ali Abbas on on 29,December,2021
 * This Class is used for
 *
 */
@Entity
data class RepositoryCommits(
    @PrimaryKey
    val repositoryName: String,
    @TypeConverters(ListToStringTypeConverterBarData::class)
    val listOfCommitsByMonths: List<BarData>?
)
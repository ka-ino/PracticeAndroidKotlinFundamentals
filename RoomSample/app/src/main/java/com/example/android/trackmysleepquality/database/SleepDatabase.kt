/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * このクラスの唯一の目的はデータベースを提供すること。
 */
@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase: RoomDatabase() {
    abstract val sleepDatabaseDao: SleepDatabaseDao

    companion object {
        // 値をキャッシュせずに、すべての書き込みと読み取りはメインメモリとの間で行う
        // これによりINSTANCEの値が常に最新であり、すべての実行スレッドで同じ結果が得られる
        // (複数のスレッドがそれぞれキャッシュ内の同じエンティティを更新し、問題が発生するような状況は発生しない)
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getInstance(context: Context): SleepDatabase{
            // 排他制御で複数のスレッドからの同時インスタンス生成を防ぐ
            // 下記ブロックを実行できるのは1度に1スレッドだけ
            synchronized(this){
                // 現在のINSTANCEをローカルにコピー
                var instance = INSTANCE

                if (instance == null) {
                    // nullならDatabaseのインスタンスを生成する
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            SleepDatabase::class.java,
                            "sleep_history_database")
                            // Schema変更時のためにMigration設定
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}




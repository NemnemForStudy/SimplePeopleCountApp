package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.repository.CountRepository
import com.example.myapplication.viewModel.CounterViewModel
import com.example.myapplication.viewModel.CounterViewModelFactory
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : ComponentActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { CountRepository(database.countRecordDao()) }
    private val viewModel: CounterViewModel by viewModels {
        CounterViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CounterScreen(viewModel)
            }
        }
    }
}

@Composable
fun CounterScreen(viewModel: CounterViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("기록 삭제") },
            text = { Text("최신 기록 1개를 제외하고 모두 삭제하시겠습니까?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteRecordsExceptMostRecent()
                        showDeleteConfirmation = false
                    }
                ) {
                    Text("확인")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteConfirmation = false }) {
                    Text("취소")
                }
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(2f) // 2/3 size
                .fillMaxWidth()
                .background(Color(0xFF4CAF50))
                .clickable { viewModel.increment() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${uiState.count}",
                fontSize = 150.sp,
                color = Color.White
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.reset() }) { Text(text = "카운트 리셋") }
            Button(onClick = { viewModel.saveRecord() }) { Text(text = "카운트 저장") }
            Button(onClick = { showDeleteConfirmation = true }) { Text(text = "데이터 삭제") }
        }

        LazyColumn(
            modifier = Modifier.weight(1f).padding(16.dp)
        ) {
            items(uiState.records) { record ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(text = sdf.format(record.date), modifier = Modifier.weight(1f))
                    Text(text = "${record.count} 명", modifier = Modifier.padding(start = 16.dp))
                }
            }
        }
    }
}
package android.dev.cryptotrackerapp.ui.theme.components

import android.dev.cryptotrackerapp.ui.screen.crypto_list.CryptoListTab
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

interface UIModel {
    @Composable
    fun getString(): String
}


@Composable
fun <T : UIModel> AppChip(
    selectedItem : T,
    items : ImmutableList<T>,
    onItemClick : (T) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO#9: Добавить кликейбл группу для обработки нажатия
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.forEach { item ->
            val isSelected = item == selectedItem
            FilterChip(
                selected = isSelected,
                onClick = { onItemClick(item) },
                label = { Text(item.getString(), modifier = Modifier.padding(horizontal = 15.dp)) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = colors.primaryColor.copy(alpha = 0.12f),
                    selectedLabelColor = colors.primaryColor,
                    containerColor = Color.LightGray,
                    labelColor = Color.Black
                ),
                modifier = Modifier.padding(horizontal = 4.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(0.dp, color = Color.White)
            )
        }
    }
}

@Preview
@Composable
fun AppChipPreview() {
    ApplicationTheme {
        AppChip(
            selectedItem = CryptoListTab.USD,
            items = CryptoListTab.entries.toImmutableList(),
            onItemClick = {}
        )
    }
}
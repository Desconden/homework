package com.example.homework.ui.home.HomeActivity


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework.Graph
import com.example.homework.data.entity.Activity
import com.example.homework.util.viewModelProviderFactoryOf
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun HomeActivity(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val viewModel: HomeActivityViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    Column(modifier = modifier){
        ActivityList(
            list = viewState.activity
        )
    }
}

@Composable
private fun ActivityList(
    list: List<Activity>
){
    LazyColumn(
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.Center
    ){
        items(list){ item -> ActivityListItem(
            activity = item,
            onClick = {},
            modifier = Modifier.fillParentMaxWidth(),
        )
        }
    }
}

@Composable
private fun ActivityListItem(
    activity : Activity,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
){
    //val activityID = activity.activityId
    val viewModel: HomeActivityViewModel = viewModel(
        //key = "activity_list_$activityID",
        //factory = viewModelProviderFactoryOf { HomeActivityViewModel(activityID) }
    )

    ConstraintLayout(modifier = modifier //editlemeyi buraya ekle üzerine tıklayınca ekleme ekranı gibi bir yere atsın
        .clickable { onClick(
        ) }) {
        val(divider, activityTitle, activityCategory, icon, date) = createRefs()
        Divider(
            Modifier.constrainAs(divider){
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                width = Dimension.fillToConstraints
            }
        )
        //title
        Text(
            text = activity.activityTitle,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.constrainAs(activityTitle){
                linkTo(
                    start = parent.start,
                    end = icon.start,
                    startMargin = 48.dp,
                    endMargin = 32.dp,
                    bias = 0f
                )
                top.linkTo(parent.top, margin = 10.dp)
                width = Dimension.preferredWrapContent
            }
        )
        //category
        Text(
            text = activity.activityCategory,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.constrainAs(activityCategory){
                linkTo(
                    start = parent.start,
                    end = icon.start,
                    startMargin = 24.dp,
                    endMargin = 8.dp
                )
                top.linkTo(activityTitle.bottom, margin = 6.dp)
                bottom.linkTo(parent.bottom, margin = 10.dp)
                width = Dimension.preferredWrapContent
            }
        )
        //Date
        Text(
            text = when {
                activity.activityDate != null -> { activity.activityDate.toDateString() }
                else -> Date().formatToString()
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(date){
                linkTo(
                    start = activityCategory.end,
                    end = icon.start,
                    startMargin = 8.dp,
                    endMargin = 16.dp
                )
                centerVerticallyTo(activityCategory)
                top.linkTo(activityTitle.bottom, 6.dp)
                bottom.linkTo(parent.bottom, 10.dp)
            }
        )

        //icon
        IconButton(
            onClick = {
                deleteActivityControl()
                viewModel.deleteActivity(activity)
                      },
            modifier = Modifier
                .size(50.dp)
                .padding(6.dp)
                .constrainAs(icon) {
                    top.linkTo(parent.top, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                    end.linkTo(parent.end)
                }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(com.example.homework.R.string.check_mark)
            )
        }
    }
}

private fun Date.formatToString(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(this)
}

fun Long.toDateString(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date(this))

}

fun deleteActivityControl(){
    val builder = android.app.AlertDialog.Builder(Graph.appContext)
    builder.setPositiveButton("Yes"){_,_ ->
        Toast.makeText(Graph.appContext,"Successfully Removed.", Toast.LENGTH_SHORT).show()
    }
    builder.setNegativeButton("No"){_,_ ->

    }
    builder.setTitle("Delete Activity")
    builder.setMessage("Are you sure you want to delete?")
    builder.create().show()
}

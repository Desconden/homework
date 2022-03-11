package com.example.homework.ui.home.HomeActivity


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import androidx.navigation.NavController
import com.example.homework.data.entity.Activity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun HomeActivity(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: HomeActivityViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    Column(modifier = modifier){
        ActivityList(
            list = viewState.activity,
            navController = navController
        )
    }
}

@Composable
private fun ActivityList(
    list: List<Activity>,
    navController: NavController
){
    LazyColumn(
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.Center
    ){
        items(list){ item -> ActivityListItem(
            activity = item,
            onClick = {},
            modifier = Modifier.fillParentMaxWidth(),
            navController = navController
        )
        }
    }
}

@Composable
private fun ActivityListItem(
    activity : Activity,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    //val activityID = activity.activityId
    val coroutineScope = rememberCoroutineScope()
    val viewModel: HomeActivityViewModel = viewModel(
        //key = "activity_list_$activityID",
        //factory = viewModelProviderFactoryOf { HomeActivityViewModel(activityID) }
    )

    ConstraintLayout(modifier = modifier //editlemeyi buraya ekle üzerine tıklayınca ekleme ekranı gibi bir yere atsın
        .clickable {
        }) {
        val (divider, activityTitle, activityCategory, icon, date, time) = createRefs()
        Divider(
            Modifier.constrainAs(divider) {
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
            modifier = Modifier.constrainAs(activityTitle) {
                linkTo(
                    start = parent.start,
                    end = activityCategory.start,
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
            modifier = Modifier.constrainAs(activityCategory) {
                linkTo(
                    start = activityTitle.end,
                    end = icon.start,
                    startMargin = 16.dp,
                    endMargin = 12.dp,
                    bias = 0f
                )
                top.linkTo(parent.top, margin = 10.dp)
                width = Dimension.preferredWrapContent
            }
        )
        //time
        Text(
            text = activity.activityTime,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(time) {
                linkTo(
                    start = parent.start,
                    end = date.start,
                    startMargin = 50.dp,
                    endMargin = 0.dp
                )
                top.linkTo(activityCategory.bottom, margin = 6.dp)
                bottom.linkTo(parent.bottom, margin = 6.dp)
                width = Dimension.preferredWrapContent
            }
        )
        //Date
        Text(
            text = activity.activityRDate
            ,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(date) {
                linkTo(
                    start = time.end,
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
                val Id = activity.activityId.toString()
                navController.navigate("Update/$Id/${activity.activityTitle}/${activity.activityCategory}/${activity.activityDesc}")
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
                imageVector = Icons.Filled.Edit,
                contentDescription = stringResource(com.example.homework.R.string.check_mark)
            )
        }
    }
    /*
fun deleteActivityControl(activityID: Long){
    val builder = android.app.AlertDialog.Builder(Graph.appContext)
    builder.setPositiveButton("Yes"){_,_ ->
        viewModel.deleteActivity(activityID)
        Toast.makeText(Graph.appContext,"Successfully Removed.", Toast.LENGTH_SHORT).show()
    }
    builder.setNegativeButton("No"){_,_ ->

    }
    builder.setTitle("Delete Activity")
    builder.setMessage("Are you sure you want to delete?")
    builder.create().show()
}
*/
}
private fun Date.formatToString(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(this)
}



fun Long.toDateString(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date(this))

}



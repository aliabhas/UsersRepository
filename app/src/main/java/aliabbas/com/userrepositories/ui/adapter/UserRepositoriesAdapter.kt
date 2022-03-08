package aliabbas.com.userrepositories.ui.adapter

import aliabbas.com.userrepositories.R
import aliabbas.com.userrepositories.databinding.ItemUserRepositoriesBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class UserRepositoriesAdapter @Inject constructor() :
    RecyclerView.Adapter<UserRepositoriesAdapter.ConfigureViewHolderForUnselected>() {
    private val defaultViewType = 0
    private var lstUserRepositoryModels: List<aliabbas.com.scalablecodebaseapp.model.user_repository.UserRepositoriesModel> =
        listOf()

    /**
     * Setting the details to display in the Recyclerview
     */
    fun setValues(_lstUserRepositoryModels: List<aliabbas.com.scalablecodebaseapp.model.user_repository.UserRepositoriesModel>) {
        this.lstUserRepositoryModels = _lstUserRepositoryModels
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConfigureViewHolderForUnselected {
        val itemUnselectBinding = DataBindingUtil.inflate<ItemUserRepositoriesBinding>(
            LayoutInflater.from(parent.context), R.layout.item_user_repositories,
            parent, false
        )
        return ConfigureViewHolderForUnselected(itemUnselectBinding)
    }

    override fun onBindViewHolder(holder: ConfigureViewHolderForUnselected, position: Int) {
        holder.bindProfileDetails(lstUserRepositoryModels[position])
    }

    /**
     * Here setting up the detail to the views
     * And adding the ClickListener
     * view is binded with the Databinding, so we just have to set the relevant object
     * and data will be populated.
     */
    inner class ConfigureViewHolderForUnselected(private var mItemPeopleBinding: ItemUserRepositoriesBinding) :
        RecyclerView.ViewHolder(mItemPeopleBinding.materialCardView) {
        fun bindProfileDetails(userRepositoriesModel: aliabbas.com.scalablecodebaseapp.model.user_repository.UserRepositoriesModel?) {
            //That's the binding object for the details to display on screen
            mItemPeopleBinding.userRepositories = userRepositoriesModel
            mItemPeopleBinding.materialCardView.setOnClickListener {
                //Displaying the bottom sheet while passing the bundles with it
                //setting up the bundles
                if (!userRepositoriesModel?.name.contentEquals("")) {
                    val bundle = bundleOf("userRepository" to userRepositoriesModel)
                    //with the help of Navigation Component navigating Displaying Dialog
                    it.findNavController().navigate(R.id.navigateTodetailFragment, bundle)
                } else {
                    Snackbar.make(
                        mItemPeopleBinding.materialCardView, "Repositories details are incorrect" +
                                " for loading it's data", Snackbar.LENGTH_LONG
                    ).show()
                }
            }

        }
    }

    /**
     * Returning the id for the view
     * Stable View
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Type of views we want to display in recyclerview
     * in our case we have only 1 view, so we are only returning only 1 type of view
     * if we have multiple view we could have returned different kind of views
     */
    override fun getItemViewType(position: Int): Int {
        return defaultViewType
    }

    /**
     * Returning the size of list
     */
    override fun getItemCount(): Int {
        return lstUserRepositoryModels.size
    }

}
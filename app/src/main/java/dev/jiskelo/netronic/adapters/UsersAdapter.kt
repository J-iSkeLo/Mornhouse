package dev.jiskelo.netronic.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jiskelo.netronic.data.entity.UserEntity
import dev.jiskelo.netronic.databinding.CartEntityBinding
import dev.jiskelo.netronic.structs.User

class UsersAdapter(
    private val listener :ClickListener,
    private val typeAdapter: TypeAdapter
) :RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var users = emptyList<User>()
    private var usersEntity = emptyList<UserEntity>()

    class ViewHolder(val binding: CartEntityBinding) :RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateUser(users : List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateUserEntity(usersEntity : List<UserEntity>) {
        this.usersEntity = usersEntity
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CartEntityBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return when (typeAdapter) {
            is TypeAdapter.UserAdapter -> users.count()
            is TypeAdapter.UserEntityAdapter -> usersEntity.count()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (typeAdapter) {
            is TypeAdapter.UserAdapter -> initUserAdapter(position, holder)
            is TypeAdapter.UserEntityAdapter -> initUserEntityAdapter(position, holder)
        }
    }

    private fun UsersAdapter.initUserAdapter(
        position: Int,
        holder: ViewHolder
    ) {
        val item = users[position]

        with(holder.binding) {
            Glide.with(root.context)
                .load(item.picture.medium)
                .into(imageUser)

            nameUser.text = item.name.getName()

            imageUser.setOnClickListener {
                listener.clickItem(item.login.uuid)
            }
        }
    }

    private fun UsersAdapter.initUserEntityAdapter(
        position: Int,
        holder: ViewHolder
    ) {
        val item = usersEntity[position]

        with(holder.binding) {
            Glide.with(root.context)
                .load(item.image)
                .into(imageUser)

            nameUser.text = item.name

            imageUser.setOnClickListener {
                listener.clickItem(item.id)
            }
        }
    }

    interface ClickListener {
        fun clickItem(id: String)
    }
}
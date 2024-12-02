import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.MediGo.R
import com.example.MediGo.databinding.ItemServiceBinding
import com.example.MediGo.databinding.ItemSpecialistBinding
import com.example.MediGo.model.Local.ServiceCategory
import com.example.MediGo.model.Local.Specialists

class SpecialistAdapter(
    private val specialists: List<Specialists>,
    private val onItemClick: (Specialists) -> Unit // Click listener parameter
) : RecyclerView.Adapter<SpecialistAdapter.SpecialistViewHolder>() {

    inner class SpecialistViewHolder(private val binding: ItemSpecialistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(specialists: Specialists) {
            binding.tvName.text = specialists.name
            Glide.with(itemView.context)
                .load(specialists.image_url)
                .into(binding.ivRoundImage)

            // Set click listener
            binding.root.setOnClickListener {
                onItemClick(specialists)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialistViewHolder {
        val binding = ItemSpecialistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SpecialistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpecialistViewHolder, position: Int) {
        holder.bind(specialists[position])
    }

    override fun getItemCount(): Int = specialists.size

}

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.MediGo.databinding.ItemServiceBinding
import com.example.MediGo.model.Local.ServiceCategory

class ServiceAdapter(
    private val services: List<ServiceCategory>,
    private val onItemClick: (ServiceCategory) -> Unit // Click listener parameter
) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    inner class ServiceViewHolder(private val binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(service: ServiceCategory) {
            binding.serviceName.text = service.name
            binding.serviceDescription.text = service.description

            // Set click listener
            binding.root.setOnClickListener {
                onItemClick(service)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ItemServiceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(services[position])
    }

    override fun getItemCount(): Int = services.size
}

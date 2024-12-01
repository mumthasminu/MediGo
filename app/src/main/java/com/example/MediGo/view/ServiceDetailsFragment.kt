import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.MediGo.R
import com.example.MediGo.databinding.FragmentDetailsBinding
import com.example.MediGo.model.Local.ServiceCategory

class ServiceDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        // Get the ServiceCategory passed as an argument
        val serviceCategory = arguments?.getSerializable("SERVICE_CATEGORY") as? ServiceCategory

        serviceCategory?.let {
            when (it.id) {
                1 -> {
                    // Show doctor details layout
                    binding.layoutDoctorDetails.visibility = View.VISIBLE
                    binding.layoutDiagnosticsDetails.visibility = View.GONE
                    binding.layoutHealthPackagesDetails.visibility = View.GONE


                    // Populate data for doctors
                    // e.g., load a list of available doctors
                }
                2 -> {
                    // Show diagnostics details layout
                    binding.layoutDoctorDetails.visibility = View.GONE
                    binding.layoutDiagnosticsDetails.visibility = View.VISIBLE
                    binding.layoutHealthPackagesDetails.visibility = View.GONE

                    // Populate data for diagnostics
                }
                3 -> {
                    // Show health packages details layout
                    binding.layoutDoctorDetails.visibility = View.GONE
                    binding.layoutDiagnosticsDetails.visibility = View.GONE
                    binding.layoutHealthPackagesDetails.visibility = View.VISIBLE

                    // Populate data for health packages
                }
            }
        }

        return binding.root
    }
}

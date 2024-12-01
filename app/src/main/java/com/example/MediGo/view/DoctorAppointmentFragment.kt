import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.MediGo.R
import com.example.MediGo.databinding.FragmentDetailsBinding
import com.example.MediGo.model.Local.ServiceCategory

class DoctorAppointmentFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        // Get the ServiceCategory passed as an argument


        return binding.root
    }
}

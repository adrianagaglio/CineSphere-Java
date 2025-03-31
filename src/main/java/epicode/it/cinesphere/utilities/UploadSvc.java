package epicode.it.cinesphere.utilities;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class UploadSvc {

     @Autowired
    private Cloudinary cloudinary;

    public String uploadFile(MultipartFile file)  {
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "cinesphere", "resource_type", "image", "public_id", file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uploadResult.get("url").toString(); // Restituisce l'URL del file caricato
    }
}

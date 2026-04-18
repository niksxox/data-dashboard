import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import javax.persistence.*;
import java.io.InputStreamReader;
import java.util.*;

@SpringBootApplication
public class NetflixApp {
    public static void main(String[] args) {
        SpringApplication.run(NetflixApp.class, args);
    }
}

@Entity
class NetflixData {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String type; // Movie or TV Show
    public String title;
    public String country;
    public String releaseYear;
}

interface NetflixRepository extends JpaRepository<NetflixData, Long> {}

@RestController
@RequestMapping("/api/netflix")
@CrossOrigin("*")
class NetflixController {
    private final NetflixRepository repo;
    public NetflixController(NetflixRepository repo) { this.repo = repo; }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        com.opencsv.CSVReader reader = new com.opencsv.CSVReader(new InputStreamReader(file.getInputStream()));
        reader.readNext(); // Skip header
        String[] line;
        while ((line = reader.readNext()) != null) {
            NetflixData data = new NetflixData();
            data.type = line[1];
            data.title = line[2];
            data.country = line[5];
            data.releaseYear = line[7];
            repo.save(data);
        }
        return "File processed and stored in DB!";
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("Movies", repo.findAll().stream().filter(d -> d.type.equals("Movie")).count());
        stats.put("TVShows", repo.findAll().stream().filter(d -> d.type.equals("TV Show")).count());
        return stats;
    }
}
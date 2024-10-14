import br.com.fiap.environment.alert.dto.AddIndexInPut;
import br.com.fiap.environment.alert.repository.ResourceIndexRepository;
import br.com.fiap.environment.alert.repository.ResourceRepository;
import br.com.fiap.environment.alert.service.IndexService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class IndexServiceTest {
    @Mock
    ResourceRepository resourceRepository;
    @Mock
    ResourceIndexRepository repository;
    @InjectMocks
    IndexService service;

    @Test
    void addFail() {
    }
}
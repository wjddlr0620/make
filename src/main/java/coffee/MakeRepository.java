package coffee;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MakeRepository extends PagingAndSortingRepository<Make, Long>{

    List<Make> findByRequestId(Long requestId);
}
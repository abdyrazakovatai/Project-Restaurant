package java15.repo;

import java15.exception.NotFoundException;
import java15.model.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    Optional<Cheque> findById(Long id);

    default Cheque getChequeById(Long id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("Cheque with " + id + " not found"));
    }
}

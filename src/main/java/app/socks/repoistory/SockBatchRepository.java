package app.socks.repoistory;

import app.socks.entity.SockBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface SockBatchRepository extends JpaRepository<SockBatch, Integer> {
    Optional<SockBatch> getByColorAndCottonPart(String color, Integer cottonPart);

    @Query("SELECT sb FROM SockBatch sb WHERE sb.color = :color AND sb.cottonPart < :cottonPart")
    List<SockBatch> getAllByColorAndCottonPartLessThan(String color, Integer cottonPart);

    @Query("SELECT sb FROM SockBatch sb WHERE sb.color = :color AND sb.cottonPart > :cottonPart")
    List<SockBatch> getAllByColorAndCottonPartMoreThan(String color, Integer cottonPart);
}

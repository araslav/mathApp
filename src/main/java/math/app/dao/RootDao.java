package math.app.dao;

import java.util.List;
import java.util.Optional;
import math.app.model.Root;

public interface RootDao {
    Root save(Root root);
    Optional<Root> findByRoot(Double root);

    List<Root> getAll();
}

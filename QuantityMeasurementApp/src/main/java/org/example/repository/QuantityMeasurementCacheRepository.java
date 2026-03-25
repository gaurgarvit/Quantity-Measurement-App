package org.example.repository;

import org.example.Entity.QuantityMeasurementEntity;
import java.util.List;
import java.util.ArrayList;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private List<QuantityMeasurementEntity> data;

    private QuantityMeasurementCacheRepository() {
        data = new ArrayList<>();
    }

    public static QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        data.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> getAll() {
        return data;
    }
}

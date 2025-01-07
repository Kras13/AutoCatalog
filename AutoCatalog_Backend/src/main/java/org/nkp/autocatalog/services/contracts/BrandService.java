package org.nkp.autocatalog.services.contracts;

import org.nkp.autocatalog.models.brands.BrandCreateModel;
import org.nkp.autocatalog.models.brands.BrandModel;

import java.util.List;

public interface BrandService {
    List<BrandModel> getAll();
    BrandModel create(BrandCreateModel model);
}

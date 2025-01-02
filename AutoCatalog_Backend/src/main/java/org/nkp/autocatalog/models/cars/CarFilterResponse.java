package org.nkp.autocatalog.models.cars;

import java.util.List;

public class CarFilterResponse {
    private List<CarFetchResponse> cars;
    private int totalPages;
    private long totalElements;

    public CarFilterResponse() {
    }

    public CarFilterResponse(List<CarFetchResponse> cars, int totalPages, long totalElements) {
        this.cars = cars;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<CarFetchResponse> getCars() {
        return cars;
    }

    public void setCars(List<CarFetchResponse> cars) {
        this.cars = cars;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}

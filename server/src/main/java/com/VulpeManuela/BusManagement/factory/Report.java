package com.VulpeManuela.BusManagement.factory;

import com.VulpeManuela.BusManagement.dto.ReservationDTO;

import java.io.IOException;
import java.util.List;

public interface Report {
    void genReport(List<ReservationDTO> reservationDTOList) throws IOException;
}

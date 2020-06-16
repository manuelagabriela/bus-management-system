package com.VulpeManuela.BusManagement.factory.impl;

import com.VulpeManuela.BusManagement.dto.ReservationDTO;
import com.VulpeManuela.BusManagement.factory.Report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportTxt implements Report {
    @Override
    public void genReport(List<ReservationDTO> reservationDTOList) throws IOException {
        File file = new File("V://BusManagement//BusManagement//raport.txt");

        //Create the file
        if (file.createNewFile())
        {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }

        StringBuilder sb = new StringBuilder();

        for (ReservationDTO it : reservationDTOList) {
            String id = Long.toString(it.getId());
            String client_id = Long.toString(it.getClient().getId());
            String firstName = it.getClient().getFirstName();
            String lastName = it.getClient().getLastName();
            String cnp = it.getClient().getCNP();
            String address = it.getClient().getAddress();
            String phone = it.getClient().getPhone();

            sb.append("Id: " + id + ", ClientID: " + client_id + ", FirstName: " + firstName + ", LastName: " +
                    lastName + ", CNP: " + cnp + ", Address:" + address + ", Phone:" + phone);
            sb.append("\n");
        }

        //Write Content
        FileWriter writer = new FileWriter(file);
        writer.write(sb.toString());
        writer.close();
    }
}

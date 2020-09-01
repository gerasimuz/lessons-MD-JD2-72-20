package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.HotelRoomDao;
import com.github.nastyasivko.project_final.dao.converter.HotelRoomConverter;
import com.github.nastyasivko.project_final.dao.entity.ApprovedOrderEntity;
import com.github.nastyasivko.project_final.dao.entity.HotelRoomEntity;
import com.github.nastyasivko.project_final.dao.repository.ApprovedOrderRepository;
import com.github.nastyasivko.project_final.dao.repository.HotelRoomRepository;
import com.github.nastyasivko.project_final.model.HotelRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.highestOneBit;
import static java.lang.Integer.parseInt;

public class DefaultHotelRoomDao implements HotelRoomDao {
    private static final Logger log = LoggerFactory.getLogger(HotelRoomDao.class);

    private static final int NUMBER_TWO_DIGIT = 10;
    private static final int NUMBER_TWO = 2;
    public static final int DAYS_FEB_NOT_LEAP_YEAR = 28;
    public static final int DAYS_FEB_LEAP_YEAR = 29;
    public static final int NUMBER_HUNDRED = 100;
    public static final int NUMBER_FOUR_HUNDRED = 400;
    public static final int DAYS_MONTH_THIRTY = 30;
    public static final int DAYS_MONTH_NOT_THIRTY = 31;

    private final HotelRoomRepository repository;
    private final ApprovedOrderRepository approvedOrderRepository;

    public DefaultHotelRoomDao(HotelRoomRepository repository, ApprovedOrderRepository approvedOrderRepository) {
        this.repository = repository;
        this.approvedOrderRepository = approvedOrderRepository;
    }

    @Override
    public Long saveHotelRoom(HotelRoom room) {
        HotelRoomEntity hotelRoomEntity = HotelRoomConverter.toEntity(room);
        repository.save(hotelRoomEntity);
        log.info("room {}{}{} save", room.getNumberRoom(), room.getName(), room.getBed());

        return hotelRoomEntity.getId();
    }

    @Override
    public void deleteHotelRoom(HotelRoom room) {
        HotelRoomEntity hotelRoomEntity = HotelRoomConverter.toEntity(room);
        repository.delete(hotelRoomEntity);
        log.info("room number {} delete", room.getNumberRoom());

    }

    @Override
    public void updateHotelRoom(HotelRoom oldHotelRoom, String name, String bed, String numberRoom) {
        if (name.equals("")) {
            name = oldHotelRoom.getName();
        }
        if (numberRoom.equals("")) {
            numberRoom = oldHotelRoom.getNumberRoom();
        }
        if (bed.equals("")) {
            bed = oldHotelRoom.getBed();
        }
        repository.updateHotelRoom(name, bed, numberRoom, oldHotelRoom.getId());
    }

    @Override
    public HotelRoom getHotelRoom(String numberRoom) {
        List<HotelRoomEntity> hotelRoomEntity = repository.findByNumberRoom(numberRoom);
        return HotelRoomConverter.fromEntity(hotelRoomEntity.get(0));
    }

    @Override
    public List<String> getNumberRoom(String nameRoom, String bed, String dateStart, String dateEnd) {
        List<HotelRoomEntity> hotelRoomEntities = repository.findByNameAndBed(nameRoom, bed);
        List<String> listNumberRoom = new ArrayList<>();
        for (int i = 0; i < hotelRoomEntities.size(); i++) {
            listNumberRoom.add(hotelRoomEntities.get(i).getNumberRoom());
        }
        List<String> listFreeNumberRoom = new ArrayList<>();
        getFreeRoomDependingOnTheDate(dateStart, dateEnd, listNumberRoom, listFreeNumberRoom);

        return listFreeNumberRoom;
    }

    private void getFreeRoomDependingOnTheDate(String dateStart, String dateEnd, List<String> listNumberRoom, List<String> listFreeNumberRoom) {
        for (int i = 0; i < listNumberRoom.size(); i++) {
            List<ApprovedOrderEntity> approvedOrderEntities = approvedOrderRepository.findByNumberRoom(parseInt(listNumberRoom.get(i)));
            int c = 0;
            for (int j = 0; j < approvedOrderEntities.size(); j++) {
                if (parseInt(listNumberRoom.get(i)) != approvedOrderEntities.get(j).getNumberRoom()) {
                    c++;
                }
            }
            if (c == approvedOrderEntities.size()) {
                listFreeNumberRoom.add(listNumberRoom.get(i));
                continue;
            }
            for (int j = 0; j < approvedOrderEntities.size(); j++) {
                String[] strStart = getArrayYearMonthDate(approvedOrderEntities.get(j).getDateStart());
                String[] strEnd = getArrayYearMonthDate(approvedOrderEntities.get(j).getDateEnd());

                List<String> listDate = new ArrayList<>();
                if (strStart[1].equals(strEnd[1])) {
                    for (int k = parseInt(strStart[2]); k <= parseInt(strEnd[2]); k++) {
                        if (k < NUMBER_TWO_DIGIT) {
                            listDate.add(strStart[1] + "-0" + k);
                        }
                        if (k >= NUMBER_TWO_DIGIT) {
                            listDate.add(strStart[1] + "-" + k);
                        }
                    }

                } else if (strStart[1].equals("12") || strStart[1].equals("01") || strStart[1].equals("03") || strStart[1].equals("05") || strStart[1].equals("07") || strStart[1].equals("08") || strStart[1].equals("10")) {
                    for (int k = parseInt(strStart[2]); k <= DAYS_MONTH_NOT_THIRTY; k++) {
                        if (k < NUMBER_TWO_DIGIT) {
                            listDate.add(strStart[1] + "-0" + k);
                        }
                        if (k >= NUMBER_TWO_DIGIT) {
                            listDate.add(strStart[1] + "-" + k);
                        }
                    }
                    for (int k = 1; k <= parseInt(strEnd[2]); k++) {
                        if (k < NUMBER_TWO_DIGIT) {
                            listDate.add(strEnd[1] + "-0" + k);
                        }
                        if (k >= NUMBER_TWO_DIGIT) {
                            listDate.add(strEnd[1] + "-" + k);
                        }
                    }
                } else if (strStart[1].equals("04") || strStart[1].equals("06") || strStart[1].equals("09") || strStart[1].equals("11")) {
                    for (int k = parseInt(strStart[2]); k <= DAYS_MONTH_THIRTY; k++) {
                        if (k < NUMBER_TWO_DIGIT) {
                            listDate.add(strStart[1] + "-0" + k);
                        }
                        if (k >= NUMBER_TWO_DIGIT) {
                            listDate.add(strStart[1] + "-" + k);
                        }
                    }
                    for (int k = 1; k <= parseInt(strEnd[2]); k++) {
                        if (k < NUMBER_TWO_DIGIT) {
                            listDate.add(strEnd[1] + "-0" + k);
                        }
                        if (k >= NUMBER_TWO_DIGIT) {
                            listDate.add(strEnd[1] + "-" + k);
                        }
                    }
                } else if (strStart[1].equals("02")) {
                    if (parseInt(strStart[0]) % 4 == 0 && (parseInt(strStart[0]) % NUMBER_HUNDRED != 0 || parseInt(strStart[0]) % NUMBER_FOUR_HUNDRED == 0)) {
                        for (int k = parseInt(strStart[2]); k <= DAYS_FEB_LEAP_YEAR; k++) {
                            if (k < NUMBER_TWO_DIGIT) {
                                listDate.add(strStart[1] + "-0" + k);
                            }
                            if (k >= NUMBER_TWO_DIGIT) {
                                listDate.add(strStart[1] + "-" + k);
                            }
                        }
                        for (int k = 1; k <= parseInt(strEnd[2]); k++) {
                            if (k < NUMBER_TWO_DIGIT) {
                                listDate.add(strEnd[1] + "-0" + k);
                            }
                            if (k >= NUMBER_TWO_DIGIT) {
                                listDate.add(strEnd[1] + "-" + k);
                            }
                        }
                    } else {
                        for (int k = parseInt(strStart[2]); k <= DAYS_FEB_NOT_LEAP_YEAR; k++) {
                            if (k < NUMBER_TWO_DIGIT) {
                                listDate.add(strStart[1] + "-0" + k);
                            }
                            if (k >= NUMBER_TWO_DIGIT) {
                                listDate.add(strStart[1] + "-" + k);
                            }
                        }
                        for (int k = 1; k <= parseInt(strEnd[2]); k++) {
                            if (k < NUMBER_TWO_DIGIT) {
                                listDate.add(strEnd[1] + "-0" + k);
                            }
                            if (k >= NUMBER_TWO_DIGIT) {
                                listDate.add(strEnd[1] + "-" + k);
                            }
                        }
                    }
                }

                String[] strUserDateStart = getArrayYearMonthDate(dateStart);
                String[] strUserDateEnd = getArrayYearMonthDate(dateEnd);

                String start = strUserDateStart[1] + "-" + strUserDateStart[2];
                String end = strUserDateEnd[1] + "-" + strUserDateEnd[2];
                int count = 0;
                for (int k = 0; k < listDate.size() - 1; k++) {
                    if (start.equals(listDate.get(k))) {
                        break;
                    }
                    count++;
                }
                for (int k = 1; k < listDate.size(); k++) {
                    if (end.equals(listDate.get(k))) {
                        break;
                    }
                    count++;
                }
                if (count == ((listDate.size() * NUMBER_TWO) - NUMBER_TWO)) {
                    listFreeNumberRoom.add(listNumberRoom.get(i));
                }
            }
        }
    }


    @Override
    public String[] getArrayYearMonthDate(String date) {
        String[] dateStr;
        String delimetr = "-";
        dateStr = date.split(delimetr);
        return dateStr;
    }

}

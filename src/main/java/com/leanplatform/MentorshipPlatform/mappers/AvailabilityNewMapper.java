package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.*;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class AvailabilityNewMapper {

    public static CreateAvailabilityNewResponseDTO convertDtoToEntity(CreateAvailabilityNewRequest createAvailabilityNewRequest) {
        CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO = new CreateAvailabilityNewResponseDTO();
        // createAvailabilityNewResponseDTO.setAvailabilityId(createAvailabilityNewRequest.getAvailabilityId());
        createAvailabilityNewResponseDTO.setScheduleId(createAvailabilityNewRequest.getScheduleId());


        //createAvailabilityNewResponseDTO.setSlotIds(createAvailabilityNewRequest.getSlotIds());
        createAvailabilityNewResponseDTO.setStartTime(createAvailabilityNewRequest.getStartTime());
        createAvailabilityNewResponseDTO.setEndTime(createAvailabilityNewRequest.getEndTime());
        createAvailabilityNewResponseDTO.setDays(createAvailabilityNewRequest.getDays());


        // createAvailabilityNewResponseDTO.setDays(availabilityNew.getDays());
        return createAvailabilityNewResponseDTO;

    }
    public static List<Slot> catchSlotIdsListAndConvertIntoStartTimeEndTime(Set<Long> slotIDsList){
        Long []arr=new Long[slotIDsList.size()];
        Long []arr1=new Long[slotIDsList.size()];
        slotIDsList.toArray(arr);
        slotIDsList.toArray(arr1);
        ArrayList<Long> ansans=new ArrayList(slotIDsList);
        //{1,3,4,5,10,11,20}
        //{1,20} and{3,5,10,11}
        ArrayList<Long> ans=findIfListIsContinuos(arr);
        ArrayList<Long> ans34=convert1 (arr1);
        List<Long> absoluteList1 = getAbsoluteValues(ans34);
        ansans.removeAll(absoluteList1);
      //ArrayList ansa12=new ArrayList(ansans);
      System.out.println(ansans);
        List<Long> absoluteList = getAbsoluteValues(ans);
        //List<Long> absoluteList1 = getAbsoluteValues(ansans);
        List ans12= convertSlotIds2(absoluteList);
       List ansfinal= convertSlotIdsIntoStartTimeEndTime(ansans);
        List<Slot> combinedList = new ArrayList<>(ans12);
        combinedList.addAll(ansfinal);
        return combinedList;
    }

    //
    public static Set<Long> convertStartTimeEndTimeIntoSlotIds(LocalTime startTime, LocalTime endTime) {
        long startTime1hour = startTime.getHour() * 2;
        long startTime1Min = (startTime.getMinute() * 2) / 60;
      //  long startTime1Sec = startTime.getSecond() * 2;
        long Starthour = startTime1hour + startTime1Min + 1;
        long endTime1hour = endTime.getHour() * 2;
        long endTime1Min = (endTime.getMinute() * 2) / 60;
        long endHour = endTime1hour + endTime1Min;
        Set s = new HashSet<>();
        if(Starthour<endHour) {
            for (long i = Starthour; i <= endHour; i++) {
                s.add(i);

            }
            return s;
        }
        else if(endHour<Starthour){
            for(long j=0;j<=endHour;j++){
                s.add(j);

            }
            for(long k=Starthour;k<=48;k++){
                s.add(k);

            }
            return s;

        }
        return s;


    }
    //to convert non continuous slot ids into startTIme and endTime

    public static List convertSlotIdsIntoStartTimeEndTime(List<Long> s) {
        //List<Long> longList = new ArrayList<>(s);
        List<Slot> list = new ArrayList<>();
        for (int i = 0; i < s.size(); i++) {
            Long ans = s.get(i);
            if (ans % 2 == 0) {
                int g = (int) (ans / 2);
                LocalTime endTime = LocalTime.of(g, 0);
                LocalTime startTime = endTime.minusMinutes(30);
                Slot slot = new Slot();
                slot.setStartTime(startTime);
                slot.setEndTime(endTime);
                list.add(slot);
            } else {
                int a = (int) (ans / 2);
                LocalTime startTime = LocalTime.of(a, 0);
                LocalTime endTime = startTime.plusMinutes(30);
                Slot slot = new Slot();
                slot.setStartTime(startTime);
                slot.setEndTime(endTime);
                list.add(slot);


            }
        }
        return list;

    }
    //to convert continuos slot ids into startTime and endTime;

    public static List convertSlotIds2(List<Long>longList) {
       // List<Long> longList = new ArrayList<>(set);
        //Long [] arr=new Long[2];
        List<Long[]> list = new ArrayList<>();
        for (int j = 0; j <= longList.size() - 2; j = j + 2) {
            Long[] arr = new Long[2];
            arr[0] = longList.get(j);
            arr[1] = longList.get(j + 1);
            list.add(arr);
        }
        List<Slot> ansList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Long[] ans = list.get(i);
            Slot slot = new Slot();
            if (ans[0] % 2 == 0) {
                int g = (int) (ans[0] / 2);
                LocalTime endTime = LocalTime.of(g, 0);
                LocalTime startTime = endTime.minusMinutes(30);
                slot.setStartTime(startTime);
            } else if (ans[0] % 2 != 0) {
                int a = (int) (ans[0] / 2);
                LocalTime startTime = LocalTime.of(a, 0);
                LocalTime endTime = startTime.plusMinutes(30);
                slot.setStartTime(startTime);
            }
            if (ans[1] % 2 == 0) {
                int g = (int) (ans[1] / 2);
                LocalTime endTime = LocalTime.of(g, 0);
                LocalTime startTime = endTime.minusMinutes(30);
                slot.setEndTime(endTime);

            } else if (ans[1] % 2 != 0) {
                int a = (int) (ans[1] / 2);
                LocalTime startTime = LocalTime.of(a, 0);
                LocalTime endTime = startTime.plusMinutes(30);
                slot.setEndTime(endTime);
            }
            ansList.add(slot);
        }
       return ansList;
    }

    public static ArrayList<Long> findIfListIsContinuos(Long[]arr) {
        ArrayList<Long> list=new ArrayList<>();
        //sort the array first,Arrays.sort(list);
        for(int i=0;i<arr.length-1;i++) {
//{1,2,3,7,8,9}

            if(Math.abs(arr[i])+1!=Math.abs(arr[i+1])) {

                //do nothing
                if(arr[i]<0) {
                    list.add(arr[i]);
                }
                else {
                    //do nothing
                }
            }
            else if(Math.abs(arr[i])+1==Math.abs(arr[i+1])) {
                arr[i+1]=arr[i+1]*(-1);
                if((arr[i])<0){
                    //do not add
                }
                else {
                    list.add(arr[i]);
                }
            }

            if(arr[arr.length-1]<0) {

                list.add(arr[arr.length-1]);
            }
        }

        return list;

    }
    public static ArrayList<Long> convert1 (Long[] arr) {
        ArrayList<Long> a1=new ArrayList<>();
        //sort the array
        for(int i =0;i<arr.length-1;i++) {

            if(Math.abs(arr[i])+1!=Math.abs(arr[i+1])){
                if(arr[i]<0) {
                    a1.add(arr[i]);
                }
            }
            else if(Math.abs(arr[i])+1==Math.abs(arr[i+1])){
                arr[i+1]=arr[i+1]*(-1);
                a1.add(arr[i]);

            }
            if(arr[arr.length-1]<0) {
                a1.add(arr[arr.length-1]);
            }

        }
        return a1;

    }
    public static List<Long> getAbsoluteValues(List<Long>ans) {
        List<Long> absoluteList = new ArrayList<>();
        for (int i = 0; i < ans.size(); i++) {
            Long value = ans.get(i);
            absoluteList.add(Math.abs(value));
        }



        return absoluteList;
    }
    public static List<SlotTimeDate>convertListIntoDto(  List<List<SlotTimeDate>> finalSetList){
        List<SlotTimeDate>ansLists=new ArrayList<>();
        for(int i=0;i<finalSetList.size();i++){
            List<SlotTimeDate> ansLists1=finalSetList.get(i);
            for(int j=0;j<ansLists1.size();j++){
                ansLists.add(ansLists1.get(j));
            }
        }
        return ansLists;

    }
    public static List<SlotTimeDate> convertLocalTimeToLocalDateTime( List<Slot>  slotsLists, LocalDate date ){
        List<SlotTimeDate>slotTimeDateAns=new ArrayList<>();
        for(int i=0;i<slotsLists.size();i++) {
            Slot slot = slotsLists.get(i);
            SlotTimeDate slotTimeDate = new SlotTimeDate();
            LocalTime startTime = slot.getStartTime();
            LocalDateTime startDateTime = date.atTime(startTime);
            LocalTime endTime = slot.getEndTime();
            LocalDateTime endDateTime = date.atTime(endTime);
            slotTimeDate.setStartDateTime(startDateTime);
            slotTimeDate.setEndDateTime(endDateTime);
            slotTimeDateAns.add(slotTimeDate);
        }
        return slotTimeDateAns;

    }
    public static List<Long> convertDaysIntoDto(List<Long>days){
        List<Long>activeDaysList=new ArrayList<>();
       // Day day=new Day();
       // List<Long>day1=new ArrayList<>();
        for(int i=0;i<days.size() ;i++){
            if(days.get(i)!=null) {
              activeDaysList.add(days.get(i));
            }

        }
//        day.setDay(day1);
//        activeDaysList.add(day);
        return activeDaysList;

    }
    public static UpdateAvailabilityNewResponseDTO convertDtoToEntityListOfAvailability(List<AvailabilityNew> avilabilityNew1){
        UpdateAvailabilityNewResponseDTO updateAvailabilityNewResponseDTO=new UpdateAvailabilityNewResponseDTO();
        for(int i=0;i<avilabilityNew1.size();i++) {
            AvailabilityNew availabilityNew = avilabilityNew1.get(i);
            //  availabilityNew.getSlotIds();
            Long day = avilabilityNew1.get(i).getDay();
            List<Slot> ans = catchSlotIdsListAndConvertIntoStartTimeEndTime(availabilityNew.getSlotIds());
            if (day == 0) {
                List<Slot> mon = ans;
                updateAvailabilityNewResponseDTO.setMon(mon);

            }
            if (day == 1) {
                List<Slot> tue = ans;
                updateAvailabilityNewResponseDTO.setTue(tue);
            }
            if (day == 2) {
                List<Slot> wed = ans;
                updateAvailabilityNewResponseDTO.setWed(wed);

            }
            if (day == 3) {
                List<Slot> thur = ans;
                updateAvailabilityNewResponseDTO.setThur(thur);
            }
            if (day == 4) {
                List<Slot> fri = ans;
                updateAvailabilityNewResponseDTO.setFri(fri);
            }
            if (day == 5) {
                List<Slot> sat = ans;
                updateAvailabilityNewResponseDTO.setSat(sat);
            }
            if (day == 6) {
                List<Slot> sun = ans;
                updateAvailabilityNewResponseDTO.setSun(sun);
            }
            //return updateAvailabilityNewResponseDTO;


        }
//        if(updateAvailabilityNewResponseDTO.getMon()==null){
//
//        }
//        if(updateAvailabilityNewResponseDTO.getTue()==null){
//
//        }
//        if(updateAvailabilityNewResponseDTO.getWed()==null){
//
//        }
//        if(updateAvailabilityNewResponseDTO.getThur()==null){
//
//        }
//        if(updateAvailabilityNewResponseDTO.getFri()==null){
//
//        }
//        if(updateAvailabilityNewResponseDTO.getSat()==null){
//
//        }
//        if(updateAvailabilityNewResponseDTO.getSun()==null){
//
//        }


        return updateAvailabilityNewResponseDTO;

        


    }
//    public static UpdateAvailabilityNewResponseDTO convertDtoToEntityListOfAvailabilityList(ArrayList list){
//        for(int i=0;i<list.size();i++){
//          AvailabilityNew availabilityNew= (AvailabilityNew) list.get(i);
//          Long day=availabilityNew.getDay();
//            List<Slot> ans = catchSlotIdsListAndConvertIntoStartTimeEndTime(availabilityNew.getSlotIds());
//          if(day==0){
//              //List<Slot> ans = catchSlotIdsListAndConvertIntoStartTimeEndTime(availabilityNew.getSlotIds());
//              List<Slot> mon = ans;
//
//          }
//          if(day==1){
//
//          }
//          if(day==2){
//
//          }
//          if(day==3){
//
//          }
//          if(day==4){
//
//          }
//          if(day==5){
//
//          }
//          if(day==6){
//
//          }
//
//        }
//
//
//    }
}










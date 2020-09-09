package coffee;

import coffee.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    private MakeRepository makeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_Make(@Payload Ordered ordered){

        if(ordered.isMe()){
            System.out.println("##### listener Make : " + ordered.toJson());

            SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMdd HHmmss");
            Date today = new Date();
            String time = format.format(today);

            Make make = new Make();
            make.setRequestId(ordered.getRequestId());
            make.setMenuId(ordered.getMenuId());
            make.setCnt(ordered.getCnt());
            make.setMakeTime(time);
            make.setMakeStaff("staff");
            make.setStatus("Made");
            makeRepository.save(make);
        }
    }

}

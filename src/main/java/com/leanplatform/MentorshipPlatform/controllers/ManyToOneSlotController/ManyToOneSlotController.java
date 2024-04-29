package com.leanplatform.MentorshipPlatform.controllers.ManyToOneSlotController;

import com.leanplatform.MentorshipPlatform.services.ManyToOneSlotFeatureService.ManyToOneSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ManyToOneSlot")
public class ManyToOneSlotController {
    @Autowired
    private ManyToOneSlotService manyToOneSlotService;

}

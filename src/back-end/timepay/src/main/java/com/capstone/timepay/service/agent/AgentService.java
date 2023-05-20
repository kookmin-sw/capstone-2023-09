package com.capstone.timepay.service.agent;

import com.capstone.timepay.controller.agent.response.AgentRegisterResponse;
import com.capstone.timepay.domain.agent.Agent;
import com.capstone.timepay.domain.agent.AgentRepository;
import com.capstone.timepay.domain.user.User;
import com.capstone.timepay.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgentService {

    private final UserRepository userRepository;
    private final AgentRepository agentRepository;

    public AgentRegisterResponse agentRegister(Long userId, User agent){
        User user = userRepository.findById(userId).orElseThrow(()
        -> new IllegalArgumentException("존재하지 않는 신청인 유저입니다."));

        AgentRegisterResponse agentRegisterResponse = new AgentRegisterResponse(
                userId, false, "알 수 없는 이유로 agentRegister 함수 실행 도중 실패했습니다.");
        if (agentRepository.findByCreatedUserAndAssignedUser(agent, user) == null) {
            agentRepository.save(Agent.builder()
                    .createdUser(agent)
                    .assignedUser(user)
                    .build()
            );
            agentRegisterResponse.setSuccess(true);
            agentRegisterResponse.setContent("등록에 성공했습니다.");

        }
        else{
            agentRegisterResponse.setSuccess(false);
            agentRegisterResponse.setContent("이미 등록된 대리인과 신청인입니다.");
        }
        return agentRegisterResponse;
    }
}

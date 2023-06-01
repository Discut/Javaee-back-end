package com.ybuse.schoolbackend.scoresys.service;

import com.ybuse.schoolbackend.scoresys.domain.vo.DeductScoreVo;
import com.ybuse.schoolbackend.scoresys.service.impl.JKDeductScore;
import com.ybuse.schoolbackend.scoresys.service.impl.JWDeductScore;
import com.ybuse.schoolbackend.scoresys.service.impl.XBDeductScore;
import com.ybuse.schoolbackend.scoresys.service.impl.XLDeductScore;
import lombok.val;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Discut
 */
public class DeductScoreFactory {
    private final Map<String, IDeductScoreOperation<DeductScoreVo>> deductScoreOperationMap = new HashMap<>();

    private DeductScoreFactory() {
        deductScoreOperationMap.put("X_L", new XLDeductScore());
        deductScoreOperationMap.put("X_B", new XBDeductScore());
        deductScoreOperationMap.put("J_W", new JWDeductScore());
        deductScoreOperationMap.put("J_K", new JKDeductScore());

    }

    public static DeductScoreFactory getInstance() {
        return InnerClass.INSTANCE;
    }

    public void operation(String type, DeductScoreVo data) {
        val iDeductScoreOperation = deductScoreOperationMap.get(type);
        if (Objects.nonNull(iDeductScoreOperation)) {
            iDeductScoreOperation.operation(data);
        }
    }

    private static class InnerClass {
        static DeductScoreFactory INSTANCE = new DeductScoreFactory();
    }

}

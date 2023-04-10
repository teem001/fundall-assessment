package com.fundallassessment.app.entities;

import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantTracker extends Base{

    private String name;
    private Integer numberOfTimes;
    private BigDecimal totalAmount;


}

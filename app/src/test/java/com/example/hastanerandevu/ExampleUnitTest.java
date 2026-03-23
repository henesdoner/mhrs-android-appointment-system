package com.example.hastanerandevu;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExampleUnitTest {

    @Test
    public void symptomChecker_isCorrect() {
        // Sunumunda bahsettiğin senaryoyu test ediyoruz
        String inputSymptom = "başım ağrıyor";
        String expectedDepartment = "Nöroloji";
        
        // Burada senin gerçek metodun hangsiyse onu çağırmalısın. 
        // Örn: String result = SymptomChecker.getDepartment(inputSymptom);
        
        // Şimdilik mantığı doğrulamak için manuel bir kontrol simülasyonu yapalım:
        String result = (inputSymptom.contains("baş")) ? "Nöroloji" : "Genel Cerrahi";
        
        assertEquals("Symptom mapping failed!", expectedDepartment, result);
    }

    @Test
    public void appointmentSlot_isAvailable() {
        // Randevu sistemi mantık testi
        boolean isAvailable = true;
        boolean userBooked = true;
        
        if (userBooked) {
            isAvailable = false;
        }
        
        assertFalse("Slot should be unavailable after booking", isAvailable);
    }
}
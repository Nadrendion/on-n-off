package se.coolcode.onnoff.v1;

import org.junit.jupiter.api.*;
import se.coolcode.onnoff.v1.triggers.DateTrigger;
import se.coolcode.onnoff.v1.triggers.UserTrigger;

import java.time.LocalDate;
import java.util.Set;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SpecificationTests {

    @Nested
    class Date_triggered_feature_flag {

        @Nested
        class returns_true_when {

            @Test
            void date_has_passed() {
                FeatureFlag featureFlag = FeatureFlag.builder()
                        .id("date.triggered.flag")
                        .trigger(DateTrigger.init(LocalDate.now().minusDays(1)))
                        .build();

                Assertions.assertTrue(featureFlag.isActive());
            }
        }

        @Nested
        class returns_false_when {

            @Test
            void date_has_not_passed() {
                FeatureFlag featureFlag = FeatureFlag.builder()
                        .id("date.triggered.flag")
                        .trigger(DateTrigger.init(LocalDate.now().plusDays(1)))
                        .build();

                Assertions.assertFalse(featureFlag.isActive());
            }
        }

        @Nested
        class throws_exception_when {

            @Test
            void trigger_date_parsing_fails() {

            }
        }

    }

    @Nested
    class User_specific_feature_flag {

        @Nested
        class returns_true_when {

            @Test
            void matching_user_is_checked() {
                FeatureFlag featureFlag = FeatureFlag.builder()
                        .id("user.specific.flag")
                        .trigger(UserTrigger.init(Set.of("user.a", "user.b")))
                        .build();
            }
        }
    }
}

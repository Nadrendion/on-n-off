package se.coolcode.onnoff.v2;

import org.junit.jupiter.api.*;
import se.coolcode.onnoff.v2.triggers.*;

import java.time.LocalDate;
import java.util.Set;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SpecificationTest {

    @AfterEach
    void clear() {
        System.clearProperty("feature.active");
    }

    @Nested
    class Setup {

        @Test
        void creates_a_kill_switch_feature_flag() {
            FeatureFlag featureFlag = FeatureFlag.builder()
                    .id("feature")
                    .trigger(BooleanTrigger.create())
                    .build();
        }

        @Test
        void creates_a_value_switch_feature_flag() {
            FeatureFlag featureFlag1 = FeatureFlag.builder()
                    .id("feature")
                    .trigger(ValueTrigger.create(Set.of("A", "B")))
                    .build();

            FeatureFlag featureFlag2 = FeatureFlag.builder()
                    .id("feature")
                    .trigger(ValueTrigger.create(Set.of(1, 2)))
                    .build();
        }

        @Test
        void creates_a_date_switch_feature_flag() {
            FeatureFlag featureFlag1 = FeatureFlag.builder()
                    .id("feature")
                    .trigger(DateTrigger.create(LocalDate.now().toString()))
                    .build();

            FeatureFlag featureFlag2 = FeatureFlag.builder()
                    .id("feature")
                    .trigger(DateTrigger.create(LocalDate.now()))
                    .build();
        }

        @Test
        void creates_a_percentage_switch_feature_flag() {
            FeatureFlag featureFlag = FeatureFlag.builder()
                    .id("feature")
                    .trigger(PercentageTrigger.create(25))
                    .build();
        }

        @Test
        void creates_a_random_switch_feature_flag() {
            FeatureFlag featureFlag = FeatureFlag.builder()
                    .id("feature")
                    .trigger(RandomTrigger.create())
                    .build();
        }
    }

    @Nested
    class Kill_switch_feature_flag {

        @Nested
        class returns_true_for_all_when {

            @Test
            void active() {
                FeatureFlag featureFlag = FeatureFlag.builder()
                        .id("feature")
                        .trigger(BooleanTrigger.create())
                        .build();
                System.setProperty("feature.active", "true");

                Assertions.assertTrue(featureFlag.isActive());
                Assertions.assertTrue(featureFlag.isActive("A"));
                Assertions.assertTrue(featureFlag.isActive(1));
            }
        }

        @Nested
        class returns_false_for_all_when {

            @Test
            void no_value_has_been_set() {
                FeatureFlag featureFlag = FeatureFlag.builder()
                        .id("feature")
                        .trigger(BooleanTrigger.create())
                        .build();

                Assertions.assertFalse(featureFlag.isActive());
                Assertions.assertFalse(featureFlag.isActive("A"));
                Assertions.assertFalse(featureFlag.isActive(1));
            }

            @Test
            void inactive() {
                FeatureFlag featureFlag = FeatureFlag.builder()
                        .id("feature")
                        .trigger(BooleanTrigger.create())
                        .build();
                System.setProperty("feature.active", "false");

                Assertions.assertFalse(featureFlag.isActive());
                Assertions.assertFalse(featureFlag.isActive("A"));
                Assertions.assertFalse(featureFlag.isActive(1));
            }
        }

        @Nested
        class throws_exception_when {

            @Test
            void a_non_boolean_value_is_set() {
                FeatureFlag featureFlag = FeatureFlag.builder()
                        .id("feature")
                        .trigger(BooleanTrigger.create())
                        .build();
                System.setProperty("feature.active", "eurt");

                Assertions.assertThrows(TriggerParsingException.class, featureFlag::isActive);
                Assertions.assertThrows(TriggerParsingException.class, () -> featureFlag.isActive("A"));
                Assertions.assertThrows(TriggerParsingException.class, () -> featureFlag.isActive(1));
            }
        }
    }
}

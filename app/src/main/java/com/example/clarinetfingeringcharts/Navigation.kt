package com.example.clarinetfingeringcharts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import java.util.Locale

// for each instrument, create a map that links each note to its image
val clarinetRange = mapOf(
    "E3" to R.drawable.e3,
    "F3" to R.drawable.f3,
    "F#3" to R.drawable.fsharp3,
    "G3" to R.drawable.g3,
    "G#3" to R.drawable.gsharp3,
    "A3" to R.drawable.a3,
    "A#3" to R.drawable.asharp3,
    "B3" to R.drawable.b3,
    "C4" to R.drawable.c4,
    "C#4" to R.drawable.csharp4,
    "D4" to R.drawable.d4,
    "D#4" to R.drawable.dsharp4,
    "E4" to R.drawable.e4,
    "F4" to R.drawable.f4,
    "F#4" to R.drawable.fsharp4,
    "G4" to R.drawable.g4,
    "G#4" to R.drawable.gsharp4,
    "A4" to R.drawable.a4,
    "A#4" to R.drawable.asharp4,
    "B4" to R.drawable.b4,
    "C5" to R.drawable.c5,
    "C#5" to R.drawable.csharp5,
    "D5" to R.drawable.d5,
    "D#5" to R.drawable.dsharp5,
    "E5" to R.drawable.e5,
    "F5" to R.drawable.f5,
    "F#5" to R.drawable.fsharp5,
    "G5" to R.drawable.g5,
    "G#5" to R.drawable.gsharp5,
    "A5" to R.drawable.a5,
    "A#5" to R.drawable.asharp5,
    "B5" to R.drawable.b5,
    "C6" to R.drawable.c6,
    "C#6" to R.drawable.csharp6,
    "D6" to R.drawable.d6,
    "D#6" to R.drawable.dsharp6,
    "E6" to R.drawable.e6,
    "F6" to R.drawable.f6,
    "F#6" to R.drawable.fsharp6,
    "G6" to R.drawable.g6,
    "G#6" to R.drawable.gsharp6,
    "A6" to R.drawable.a6,
    "A#6" to R.drawable.asharp6,
    "B6" to R.drawable.b6,
    "C7" to R.drawable.c7,
    "C#7" to R.drawable.csharp7,
    "D7" to R.drawable.d7,
    "D#7" to R.drawable.dsharp7,
    "E7" to R.drawable.e7,
    "F7" to R.drawable.f7,
    "F#7" to R.drawable.fsharp7,
    "G7" to R.drawable.g7,
    "G#7" to R.drawable.gsharp7,
    "A7" to R.drawable.a7
)

val bassClarinetRange = mapOf(
    "C3" to R.drawable.c3,
    "C#3" to R.drawable.csharp3,
    "D3" to R.drawable.d3,
    "D#3" to R.drawable.dsharp3,
    "E3" to R.drawable.e3,
    "F3" to R.drawable.f3,
    "F#3" to R.drawable.fsharp3,
    "G3" to R.drawable.g3,
    "G#3" to R.drawable.gsharp3,
    "A3" to R.drawable.a3,
    "A#3" to R.drawable.asharp3,
    "B3" to R.drawable.b3,
    "C4" to R.drawable.c4,
    "C#4" to R.drawable.csharp4,
    "D4" to R.drawable.d4,
    "D#4" to R.drawable.dsharp4,
    "E4" to R.drawable.e4,
    "F4" to R.drawable.f4,
    "F#4" to R.drawable.fsharp4,
    "G4" to R.drawable.g4,
    "G#4" to R.drawable.gsharp4,
    "A4" to R.drawable.a4,
    "A#4" to R.drawable.asharp4,
    "B4" to R.drawable.b4,
    "C5" to R.drawable.c5,
    "C#5" to R.drawable.csharp5,
    "D5" to R.drawable.d5,
    "D#5" to R.drawable.dsharp5,
    "E5" to R.drawable.e5,
    "F5" to R.drawable.f5,
    "F#5" to R.drawable.fsharp5,
    "G5" to R.drawable.g5,
    "G#5" to R.drawable.gsharp5,
    "A5" to R.drawable.a5,
    "A#5" to R.drawable.asharp5,
    "B5" to R.drawable.b5,
    "C6" to R.drawable.c6,
    "C#6" to R.drawable.csharp6,
    "D6" to R.drawable.d6,
    "D#6" to R.drawable.dsharp6,
    "E6" to R.drawable.e6,
    "F6" to R.drawable.f6,
    "F#6" to R.drawable.fsharp6,
    "G6" to R.drawable.g6,
    "G#6" to R.drawable.gsharp6,
    "A6" to R.drawable.a6,
    "A#6" to R.drawable.asharp6,
    "B6" to R.drawable.b6,
    "C7" to R.drawable.c7,
    "C#7" to R.drawable.csharp7,
    "D7" to R.drawable.d7,
    "D#7" to R.drawable.dsharp7,
    "E7" to R.drawable.e7,
    "F7" to R.drawable.f7,
    "F#7" to R.drawable.fsharp7,
    "G7" to R.drawable.g7,
    "G#7" to R.drawable.gsharp7,
    "A7" to R.drawable.a7
)

// Maps each note to another map of the fingering description to the image
// (because the same note can have multiple different fingerings for different situations)
val clarinetFingerings = mapOf(
    "E3" to mapOf(
        R.string._1_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_e3_1,
        R.string._2_basic_fingering to R.drawable.cl_e3_2,
        R.string._3_good_for_fast_switch_to_f3 to R.drawable.cl_e3_3
    ),
    "F3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_f3_1,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_f3_2
    ),
    "F#3" to mapOf(
        R.string._1_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_fsharp3_1,
        R.string._2_basic_fingering to R.drawable.cl_fsharp3_2,
        R.string._3_good_for_fast_switch_to_f3 to R.drawable.cl_fsharp3_3
    ),
    "G3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_g3_1
    ),
    "G#3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_gsharp3_1,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_gsharp3_2
    ),
    "A3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_a3_1
    ),
    "A#3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_asharp3_1
    ),
    "B3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_b3_1,
        R.string._2_good_for_fast_switch_to_a_3 to R.drawable.cl_b3_2
    ),
    "C4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_c4_1
    ),
    "C#4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_csharp4_1
    ),
    "D4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_d4_1
    ),
    "D#4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_dsharp4_1,
        R.string._2_basic_fingering to R.drawable.cl_dsharp4_2,
        R.string._3_sharp_use_with_fingerings_using_right_hand to R.drawable.cl_dsharp4_3,
        R.string._4_sharp_use_with_fingerings_using_right_hand to R.drawable.cl_dsharp4_4,
        R.string._5_good_for_fast_switch_to_c4 to R.drawable.cl_dsharp4_5
    ),
    "E4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_e4_1,
        R.string._2_lower_pitch_than_basic_fingering to R.drawable.cl_e4_2,
        R.string._3_louder_clearer_sound_good_for_long_notes to R.drawable.cl_e4_3
    ),
    "F4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_f4_1,
    ),
    "F#4" to mapOf(
        R.string._1_basic_fingering_use_right_hand_to_control_tone to R.drawable.cl_fsharp4_1,
        R.string._2_good_for_fast_switch_to_f4_use_right_hand_to_control_tone to R.drawable.cl_fsharp4_2,
    ),
    "G4" to mapOf(
        R.string._1_basic_fingering_use_right_hand_to_control_tone to R.drawable.cl_g4_1
    ),
    "G#4" to mapOf(
        R.string._1_basic_fingering_use_right_hand_to_control_tone to R.drawable.cl_gsharp4_1,
        R.string._2_good_for_fast_switch_to_f_4_use_right_hand_to_control_tone to R.drawable.cl_gsharp4_2,
        R.string._3_good_for_fast_switch_to_e4_slightly_sharp to R.drawable.cl_gsharp4_3,
        R.string._4_slightly_flat to R.drawable.cl_gsharp4_4
    ),
    "A4" to mapOf(
        R.string._1_basic_fingering_use_right_hand_to_control_tone to R.drawable.cl_a4_1,
        R.string._2_fuller_sound_less_airy to R.drawable.cl_a4_2,
        R.string._3_slightly_flat to R.drawable.cl_a4_3
    ),
    "A#4" to mapOf(
        R.string._1_basic_fingering_use_right_hand_to_control_tone to R.drawable.cl_asharp4_1,
        R.string._2_good_for_fast_switch_to_a4_use_right_hand_to_control_tone to R.drawable.cl_asharp4_2,
        R.string._3_more_in_tune_than_basic_fingering to R.drawable.cl_asharp4_3,
        R.string._4_more_in_tune_than_basic_fingering to R.drawable.cl_asharp4_4,
        R.string._5_better_tone_than_basic_fingering to R.drawable.cl_asharp4_5,
        R.string._6_better_tone_than_basic_fingering to R.drawable.cl_asharp4_6,
        R.string._7_less_airy_than_basic_fingering to R.drawable.cl_asharp4_7,
    ),
    "B4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_b4_2,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_b4_1,
        R.string._3_good_for_fast_switch_to_c5 to R.drawable.cl_b4_3,
        R.string._4_good_for_fast_switch_to_a4 to R.drawable.cl_b4_4,
        R.string._5_good_for_fast_switch_to_a_4 to R.drawable.cl_b4_5,
        R.string._6_useful_for_longer_notes to R.drawable.cl_b4_6,
        R.string._7_useful_for_longer_notes to R.drawable.cl_b4_7,
        R.string._8_useful_for_longer_notes to R.drawable.cl_b4_8
    ),
    "C5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_c5_2,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_c5_1,
        R.string._3_good_for_fast_switch_to_a_4 to R.drawable.cl_c5_3,
        R.string._4_good_for_fast_switch_to_a_4_more_in_tune_than_previous to R.drawable.cl_c5_4
    ),
    "C#5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_csharp5_2,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_csharp5_1,
        R.string._3_good_for_fast_switch_to_c5 to R.drawable.cl_csharp5_3,
    ),
    "D5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_d5_1,
    ),
    "D#5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_dsharp5_1,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_dsharp5_2
    ),
    "E5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_e5_1,
    ),
    "F5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_f5_1,
    ),
    "F#5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_fsharp5_1,
        R.string._2_good_for_fast_switch_to_f5 to R.drawable.cl_fsharp5_2,
        R.string._3_good_for_fast_switch_to_d5 to R.drawable.cl_fsharp5_3,
    ),
    "G5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_g5_1,
    ),
    "G#5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_gsharp5_1,
        R.string._2_use_with_fingerings_using_right_hand to R.drawable.cl_gsharp5_2
    ),
    "A5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_a5_1,
    ),
    "A#5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_asharp5_1,
        R.string._2_good_for_fast_switch_to_a5 to R.drawable.cl_asharp5_2,
        R.string._3_good_for_fast_switch_to_g_5 to R.drawable.cl_asharp5_3,
        R.string._4_use_with_fingerings_using_right_hand to R.drawable.cl_asharp5_4,
        R.string._5_use_with_fingerings_using_right_hand_slightly_sharp to R.drawable.cl_asharp5_5,
        R.string._6_good_for_fast_switch_to_g5 to R.drawable.cl_asharp5_6,
        R.string._7_good_for_playing_very_softly to R.drawable.cl_asharp5_7,
    ),
    "B5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_b5_1,
        R.string._2_good_for_fast_switch_to_lower_altissimo_notes to R.drawable.cl_b5_2
    ),
    "C6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_c6_1,
        R.string._2_good_for_fast_switch_to_d5_and_playing_very_softly to R.drawable.cl_c6_2,
        R.string._3_good_for_large_intervals_i_e_d5_and_playing_very_softly to R.drawable.cl_c6_3,
        R.string._4_good_for_fast_switch_to_a_5_and_playing_very_loudly_slightly_sharp to R.drawable.cl_c6_4,
        R.string._5_good_for_fast_switch_to_d6 to R.drawable.cl_c6_5,
        R.string._6_good_for_playing_very_loudly to R.drawable.cl_c6_6,
        R.string._7_good_for_playing_very_loudly to R.drawable.cl_c6_7
    ),
    "C#6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_csharp6_1,
        R.string._2_good_for_fast_switch_to_c6 to R.drawable.cl_csharp6_2,
        R.string._3_good_for_fast_switch_to_b5 to R.drawable.cl_csharp6_3,
        R.string._4_harmonic to R.drawable.cl_csharp6_4,
        R.string._5_harmonic to R.drawable.cl_csharp6_5,
        R.string._6_good_for_playing_very_softly to R.drawable.cl_csharp6_6
    ),
    "D6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_d6_1,
        R.string._2_good_for_fast_switch_to_c6 to R.drawable.cl_d6_2,
        R.string._3_good_for_fast_switch_to_c6 to R.drawable.cl_d6_3,
        R.string._4_good_for_fast_switch_to_c6 to R.drawable.cl_d6_4,
        R.string._5_harmonic to R.drawable.cl_d6_5,
        R.string._6_good_for_playing_very_softly to R.drawable.cl_d6_6,
        R.string._7_good_for_large_intervals_and_playing_very_softly to R.drawable.cl_d6_7,
        R.string._8_good_for_playing_very_loudly to R.drawable.cl_d6_8
    ),
    "D#6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_dsharp6_1,
        R.string._2_good_for_fast_switch_with_lower_notes to R.drawable.cl_dsharp6_2,
        R.string._3_good_for_fast_switch_to_c_6_slightly_flat to R.drawable.cl_dsharp6_3,
        R.string._4_harmonic to R.drawable.cl_dsharp6_4,
        R.string._5_good_for_playing_very_softly to R.drawable.cl_dsharp6_5,
    ),
    "E6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_e6_1,
        R.string._2_no_information to R.drawable.cl_e6_2,
        R.string._3_no_information to R.drawable.cl_e6_3,
    ),
    "F6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_f6_1,
        R.string._2_good_for_fast_switch_to_lower_notes_and_playing_very_softly to R.drawable.cl_f6_2,
        R.string._3_good_for_large_intervals to R.drawable.cl_f6_3,
        R.string._4_good_for_playing_very_loudly to R.drawable.cl_f6_4,
        R.string._5_no_information to R.drawable.cl_f6_5
    ),
    "F#6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_fsharp6_1,
        R.string._2_good_for_fast_switch_to_lower_notes_playing_very_softly_and_playing_very_loudly to R.drawable.cl_fsharp6_2,
        R.string._3_good_for_large_intervals_playing_very_softly_and_playing_very_loudly to R.drawable.cl_fsharp6_3,
        R.string._4_no_information to R.drawable.cl_fsharp6_4,
        R.string._5_good_for_playing_very_loudly to R.drawable.cl_fsharp6_5,
        R.string._6_good_for_fast_switch_to_lower_notes_and_playing_very_softly to R.drawable.cl_fsharp6_6,
        R.string._7_no_information to R.drawable.cl_fsharp6_7,
        R.string._8_no_information to R.drawable.cl_fsharp6_8,
        R.string._9_no_information to R.drawable.cl_fsharp6_9,
        R.string._10_good_for_fast_switch_to_e6_and_g_6 to R.drawable.cl_fsharp6_10
    ),
    "G6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_g6_1,
        R.string._2_good_for_fast_switch_to_lower_notes to R.drawable.cl_g6_2,
        R.string._3_no_information to R.drawable.cl_g6_3,
        R.string._4_slightly_sharper to R.drawable.cl_g6_4,
        R.string._5_no_information to R.drawable.cl_g6_5,
        R.string._6_good_for_fast_switch_to_d6 to R.drawable.cl_g6_6,
        R.string._7_good_for_fast_switch_to_f6 to R.drawable.cl_g6_7,
        R.string._8_harmonic to R.drawable.cl_g6_8,
        R.string._9_no_information to R.drawable.cl_g6_9,
        R.string._10_no_information to R.drawable.cl_g6_10,
        R.string._11_no_information to R.drawable.cl_g6_11,
        R.string._12_no_information to R.drawable.cl_g6_12,
        R.string._13_good_for_fast_switch_to_f_6 to R.drawable.cl_g6_13,
        R.string._14_no_information to R.drawable.cl_g6_14,
        R.string._15_no_information to R.drawable.cl_g6_15,
        R.string._16_good_for_fast_switch_to_f_6 to R.drawable.cl_g6_16,
        R.string._17_good_for_fast_switch_to_e6_and_g_6 to R.drawable.cl_g6_17,
    ),
    "G#6" to mapOf(
        R.string._1_no_information to R.drawable.cl_gsharp6_1,
        R.string._2_no_information to R.drawable.cl_gsharp6_2,
        R.string._3_good_for_all_dynamics to R.drawable.cl_gsharp6_3,
        R.string._4_good_for_fast_switch_to_d_6 to R.drawable.cl_gsharp6_4,
        R.string._5_good_for_playing_very_loudly to R.drawable.cl_gsharp6_5,
        R.string._6_no_information to R.drawable.cl_gsharp6_6,
        R.string._7_no_information to R.drawable.cl_gsharp6_7,
        R.string._8_no_information to R.drawable.cl_gsharp6_8,
        R.string._9_no_information to R.drawable.cl_gsharp6_9,
        R.string._10_no_information to R.drawable.cl_gsharp6_10,
        R.string._11_no_information to R.drawable.cl_gsharp6_11,
        R.string._12_no_information to R.drawable.cl_gsharp6_12,
        R.string._13_no_information to R.drawable.cl_gsharp6_13,
        R.string._14_no_information to R.drawable.cl_gsharp6_14,
        R.string._15_no_information to R.drawable.cl_gsharp6_15,
        R.string._16_slightly_sharp to R.drawable.cl_gsharp6_16,
        R.string._17_no_information to R.drawable.cl_gsharp6_17,
        R.string._18_good_for_fast_switch_to_e6 to R.drawable.cl_gsharp6_18,
    ),
    "A6" to mapOf(
        R.string._1_no_information to R.drawable.cl_a6_1,
        R.string._2_no_information to R.drawable.cl_a6_2,
        R.string._3_no_information to R.drawable.cl_a6_3,
        R.string._4_no_information to R.drawable.cl_a6_4,
        R.string._5_no_information to R.drawable.cl_a6_5,
        R.string._6_no_information to R.drawable.cl_a6_6,
        R.string._7_no_information to R.drawable.cl_a6_7,
        R.string._8_no_information to R.drawable.cl_a6_8,
        R.string._9_no_information to R.drawable.cl_a6_9,
        R.string._10_no_information to R.drawable.cl_a6_10,
    ),
    "A#6" to mapOf(
        R.string._1_no_information to R.drawable.cl_asharp6_1,
        R.string._2_no_information to R.drawable.cl_asharp6_2,
        R.string._3_no_information to R.drawable.cl_asharp6_3,
        R.string._4_no_information to R.drawable.cl_asharp6_4,
        R.string._5_no_information to R.drawable.cl_asharp6_5,
        R.string._6_no_information to R.drawable.cl_asharp6_6,
        R.string._7_no_information to R.drawable.cl_asharp6_7,
        R.string._8_slightly_sharp to R.drawable.cl_asharp6_8,
        R.string._9_no_information to R.drawable.cl_asharp6_9,
        R.string._10_no_information to R.drawable.cl_asharp6_10,
        R.string._11_no_information to R.drawable.cl_asharp6_11,
        R.string._12_no_information to R.drawable.cl_asharp6_12,
        R.string._13_slightly_sharp to R.drawable.cl_asharp6_13,
    ),
    "B6" to mapOf(
        R.string._1_no_information to R.drawable.cl_b6_1,
        R.string._2_no_information to R.drawable.cl_b6_2,
        R.string._3_no_information to R.drawable.cl_b6_3,
        R.string._4_no_information to R.drawable.cl_b6_4,
        R.string._5_no_information to R.drawable.cl_b6_5,
        R.string._6_no_information to R.drawable.cl_b6_6,
        R.string._7_no_information to R.drawable.cl_b6_7,
    ),
    "C7" to mapOf(
        R.string._1_no_information to R.drawable.cl_c7_1,
        R.string._2_no_information to R.drawable.cl_c7_2,
        R.string._3_no_information to R.drawable.cl_c7_3,
        R.string._4_no_information to R.drawable.cl_c7_4,
        R.string._5_no_information to R.drawable.cl_c7_5,
        R.string._6_no_information to R.drawable.cl_c7_6,
        R.string._7_no_information to R.drawable.cl_c7_7,
        R.string._8_no_information to R.drawable.cl_c7_8,
        R.string._9_no_information to R.drawable.cl_c7_9,
        R.string._10_no_information to R.drawable.cl_c7_10,
        R.string._11_no_information to R.drawable.cl_c7_11,
        R.string._12_no_information to R.drawable.cl_c7_12,
        R.string._13_no_information to R.drawable.cl_c7_13,
    ),
    "C#7" to mapOf(
        R.string._1_no_information to R.drawable.cl_csharp7_1,
        R.string._2_no_information to R.drawable.cl_csharp7_2,
        R.string._3_no_information to R.drawable.cl_csharp7_3,
        R.string._4_no_information to R.drawable.cl_csharp7_4,
        R.string._5_no_information to R.drawable.cl_csharp7_5,
        R.string._6_no_information to R.drawable.cl_csharp7_6,
    ),
    "D7" to mapOf(
        R.string._1_no_information to R.drawable.cl_d7_1,
        R.string._2_no_information to R.drawable.cl_d7_2,
        R.string._3_no_information to R.drawable.cl_d7_3,
        R.string._4_no_information to R.drawable.cl_d7_4,
        R.string._5_no_information to R.drawable.cl_d7_5,
   ),
    "D#7" to mapOf(
        R.string._1_no_information to R.drawable.cl_dsharp7_1,
        R.string._2_no_information to R.drawable.cl_dsharp7_2,
    ),
    "E7" to mapOf(
        R.string._1_no_information to R.drawable.cl_e7_1,
        R.string._2_no_information to R.drawable.cl_e7_2,
        R.string._3_good_for_all_dynamics to R.drawable.cl_e7_3,
    ),
    "F7" to mapOf(
        R.string._1_no_information to R.drawable.cl_f7_1,
        R.string._2_no_information to R.drawable.cl_f7_2,
    ),
    "F#7" to mapOf(
        R.string._1_no_information to R.drawable.cl_fsharp7_1,
        R.string._2_no_information to R.drawable.cl_fsharp7_2,
    ),
    "G7" to mapOf(
        R.string._1_no_information to R.drawable.cl_g7_1,
        R.string._2_no_information to R.drawable.cl_g7_2,
    ),
    "G#7" to mapOf(
        R.string._1_no_information to R.drawable.cl_gsharp7_1,
    ),
    "A7" to mapOf(
        R.string._1_slightly_flat_good_for_playing_very_loudly to R.drawable.cl_a7_1,
        R.string._2_slightly_sharp_good_for_playing_very_softly to R.drawable.cl_a7_1,
    )
)

// bass clarinet fingerings are mostly the same as regular clarinet with a few differences
val BassClarinetFingerings = mapOf(
    "C3" to mapOf(
        R.string._1_low_c_models_only to R.drawable.cl_c3_1
    ),
    "C#3" to mapOf(
        R.string._1_low_c_models_only to R.drawable.cl_csharp3_1,
    ),
    "D3" to mapOf(
        R.string._1_low_c_models_only_location_of_key_may_vary to R.drawable.cl_d3_1,
        R.string._2_low_c_models_only_location_of_key_may_vary to R.drawable.cl_d3_2,
        R.string._3_low_c_models_only_location_of_key_may_vary to R.drawable.cl_d3_3,
    ),
    "D#3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_dsharp3_1
    ),
    "E3" to mapOf(
        R.string._1_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_e3_1,
        R.string._2_basic_fingering to R.drawable.cl_e3_2,
        R.string._3_good_for_fast_switch_to_f3 to R.drawable.cl_e3_3
    ),
    "F3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_f3_1,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_f3_2
    ),
    "F#3" to mapOf(
        R.string._1_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_fsharp3_1,
        R.string._2_basic_fingering to R.drawable.cl_fsharp3_2,
        R.string._3_good_for_fast_switch_to_f3 to R.drawable.cl_fsharp3_3
    ),
    "G3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_g3_1
    ),
    "G#3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_gsharp3_1,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_gsharp3_2
    ),
    "A3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_a3_1
    ),
    "A#3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_asharp3_1
    ),
    "B3" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_b3_1,
        R.string._2_good_for_fast_switch_to_a_3 to R.drawable.cl_b3_2
    ),
    "C4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_c4_1
    ),
    "C#4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_csharp4_1
    ),
    "D4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_d4_1
    ),
    "D#4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_dsharp4_1,
        R.string._2_basic_fingering to R.drawable.cl_dsharp4_2,
        R.string._3_sharp_use_with_fingerings_using_right_hand to R.drawable.cl_dsharp4_3,
        R.string._4_sharp_use_with_fingerings_using_right_hand to R.drawable.cl_dsharp4_4,
        R.string._5_good_for_fast_switch_to_c4 to R.drawable.cl_dsharp4_5
    ),
    "E4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_e4_1,
        R.string._2_lower_pitch_than_basic_fingering to R.drawable.cl_e4_2,
        R.string._3_louder_clearer_sound_good_for_long_notes to R.drawable.cl_e4_3
    ),
    "F4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_f4_1,
    ),
    "F#4" to mapOf(
        R.string._1_basic_fingering_use_right_hand_to_control_tone to R.drawable.cl_fsharp4_1,
        R.string._2_good_for_fast_switch_to_f4_use_right_hand_to_control_tone to R.drawable.cl_fsharp4_2,
    ),
    "G4" to mapOf(
        R.string._1_basic_fingering_use_right_hand_to_control_tone to R.drawable.cl_g4_1
    ),
    "G#4" to mapOf(
        R.string._1_basic_fingering_use_right_hand_to_control_tone to R.drawable.cl_gsharp4_1,
        R.string._2_good_for_fast_switch_to_f_4_use_right_hand_to_control_tone to R.drawable.cl_gsharp4_2,
        R.string._3_good_for_fast_switch_to_e4_slightly_sharp to R.drawable.cl_gsharp4_3,
        R.string._4_slightly_flat to R.drawable.cl_gsharp4_4
    ),
    "A4" to mapOf(
        R.string._1_basic_fingering_use_right_hand_to_control_tone to R.drawable.cl_a4_1,
        R.string._2_fuller_sound_less_airy to R.drawable.cl_a4_2,
        R.string._3_slightly_flat to R.drawable.cl_a4_3
    ),
    "A#4" to mapOf(
        R.string._1_basic_fingering_use_right_hand_to_control_tone to R.drawable.cl_asharp4_1,
        R.string._2_good_for_fast_switch_to_a4_use_right_hand_to_control_tone to R.drawable.cl_asharp4_2,
        R.string._3_more_in_tune_than_basic_fingering to R.drawable.cl_asharp4_3,
        R.string._4_more_in_tune_than_basic_fingering to R.drawable.cl_asharp4_4,
        R.string._5_better_tone_than_basic_fingering to R.drawable.cl_asharp4_5,
        R.string._6_better_tone_than_basic_fingering to R.drawable.cl_asharp4_6,
        R.string._7_less_airy_than_basic_fingering to R.drawable.cl_asharp4_7,
    ),
    "B4" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_b4_2,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_b4_1,
        R.string._3_good_for_fast_switch_to_c5 to R.drawable.cl_b4_3,
        R.string._4_good_for_fast_switch_to_a4 to R.drawable.cl_b4_4,
        R.string._5_good_for_fast_switch_to_a_4 to R.drawable.cl_b4_5,
        R.string._6_useful_for_longer_notes to R.drawable.cl_b4_6,
        R.string._7_useful_for_longer_notes to R.drawable.cl_b4_7,
        R.string._8_useful_for_longer_notes to R.drawable.cl_b4_8
    ),
    "C5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_c5_2,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_c5_1,
        R.string._3_good_for_fast_switch_to_a_4 to R.drawable.cl_c5_3,
        R.string._4_good_for_fast_switch_to_a_4_more_in_tune_than_previous to R.drawable.cl_c5_4
    ),
    "C#5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_csharp5_2,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_csharp5_1,
        R.string._3_good_for_fast_switch_to_c5 to R.drawable.cl_csharp5_3,
    ),
    "D5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_d5_1,
    ),
    "D#5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_dsharp5_1,
        R.string._2_use_if_your_right_pinky_can_t_move_fast_enough to R.drawable.cl_dsharp5_2
    ),
    "E5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_e5_1,
    ),
    "F5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_f5_1,
    ),
    "F#5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_fsharp5_1,
        R.string._2_good_for_fast_switch_to_f5 to R.drawable.cl_fsharp5_2,
        R.string._3_good_for_fast_switch_to_d5 to R.drawable.cl_fsharp5_3,
    ),
    "G5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_g5_1,
    ),
    "G#5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_gsharp5_1,
        R.string._2_use_with_fingerings_using_right_hand to R.drawable.cl_gsharp5_2
    ),
    "A5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_a5_1,
    ),
    "A#5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_asharp5_1,
        R.string._2_good_for_fast_switch_to_a5 to R.drawable.cl_asharp5_2,
        R.string._3_good_for_fast_switch_to_g_5 to R.drawable.cl_asharp5_3,
        R.string._4_use_with_fingerings_using_right_hand to R.drawable.cl_asharp5_4,
        R.string._5_use_with_fingerings_using_right_hand_slightly_sharp to R.drawable.cl_asharp5_5,
        R.string._6_good_for_fast_switch_to_g5 to R.drawable.cl_asharp5_6,
        R.string._7_good_for_playing_very_softly to R.drawable.cl_asharp5_7,
    ),
    "B5" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_b5_1,
        R.string._2_good_for_fast_switch_to_lower_altissimo_notes to R.drawable.cl_b5_2
    ),
    "C6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_c6_1,
        R.string._2_good_for_fast_switch_to_d5_and_playing_very_softly to R.drawable.cl_c6_2,
        R.string._3_good_for_large_intervals_i_e_d5_and_playing_very_softly to R.drawable.cl_c6_3,
        R.string._4_good_for_fast_switch_to_a_5_and_playing_very_loudly_slightly_sharp to R.drawable.cl_c6_4,
        R.string._5_good_for_fast_switch_to_d6 to R.drawable.cl_c6_5,
        R.string._6_good_for_playing_very_loudly to R.drawable.cl_c6_6,
        R.string._7_good_for_playing_very_loudly to R.drawable.cl_c6_7
    ),
    "C#6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_csharp6_1,
        R.string._2_good_for_fast_switch_to_c6 to R.drawable.cl_csharp6_2,
        R.string._3_good_for_fast_switch_to_b5 to R.drawable.cl_csharp6_3,
        R.string._4_harmonic to R.drawable.cl_csharp6_4,
        R.string._5_harmonic to R.drawable.cl_csharp6_5,
        R.string._6_good_for_playing_very_softly to R.drawable.cl_csharp6_6
    ),
    "D6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_d6_1,
        R.string._2_good_for_fast_switch_to_c6 to R.drawable.cl_d6_2,
        R.string._3_good_for_fast_switch_to_c6 to R.drawable.cl_d6_3,
        R.string._4_good_for_fast_switch_to_c6 to R.drawable.cl_d6_4,
        R.string._5_harmonic to R.drawable.cl_d6_5,
        R.string._6_good_for_playing_very_softly to R.drawable.cl_d6_6,
        R.string._7_good_for_large_intervals_and_playing_very_softly to R.drawable.cl_d6_7,
        R.string._8_good_for_playing_very_loudly to R.drawable.cl_d6_8
    ),
    "D#6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_dsharp6_1,
        R.string._2_good_for_fast_switch_with_lower_notes to R.drawable.cl_dsharp6_2,
        R.string._3_good_for_fast_switch_to_c_6_slightly_flat to R.drawable.cl_dsharp6_3,
        R.string._4_harmonic to R.drawable.cl_dsharp6_4,
        R.string._5_good_for_playing_very_softly to R.drawable.cl_dsharp6_5,
    ),
    "E6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_e6_1,
        R.string._2_no_information to R.drawable.cl_e6_2,
        R.string._3_no_information to R.drawable.cl_e6_3,
    ),
    "F6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_f6_1,
        R.string._2_good_for_fast_switch_to_lower_notes_and_playing_very_softly to R.drawable.cl_f6_2,
        R.string._3_good_for_large_intervals to R.drawable.cl_f6_3,
        R.string._4_good_for_playing_very_loudly to R.drawable.cl_f6_4,
        R.string._5_no_information to R.drawable.cl_f6_5
    ),
    "F#6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_fsharp6_1,
        R.string._2_good_for_fast_switch_to_lower_notes_playing_very_softly_and_playing_very_loudly to R.drawable.cl_fsharp6_2,
        R.string._3_good_for_large_intervals_playing_very_softly_and_playing_very_loudly to R.drawable.cl_fsharp6_3,
        R.string._4_no_information to R.drawable.cl_fsharp6_4,
        R.string._5_good_for_playing_very_loudly to R.drawable.cl_fsharp6_5,
        R.string._6_good_for_fast_switch_to_lower_notes_and_playing_very_softly to R.drawable.cl_fsharp6_6,
        R.string._7_no_information to R.drawable.cl_fsharp6_7,
        R.string._8_no_information to R.drawable.cl_fsharp6_8,
        R.string._9_no_information to R.drawable.cl_fsharp6_9,
        R.string._10_good_for_fast_switch_to_e6_and_g_6 to R.drawable.cl_fsharp6_10
    ),
    "G6" to mapOf(
        R.string._1_basic_fingering to R.drawable.cl_g6_1,
        R.string._2_good_for_fast_switch_to_lower_notes to R.drawable.cl_g6_2,
        R.string._3_no_information to R.drawable.cl_g6_3,
        R.string._4_slightly_sharper to R.drawable.cl_g6_4,
        R.string._5_no_information to R.drawable.cl_g6_5,
        R.string._6_good_for_fast_switch_to_d6 to R.drawable.cl_g6_6,
        R.string._7_good_for_fast_switch_to_f6 to R.drawable.cl_g6_7,
        R.string._8_harmonic to R.drawable.cl_g6_8,
        R.string._9_no_information to R.drawable.cl_g6_9,
        R.string._10_no_information to R.drawable.cl_g6_10,
        R.string._11_no_information to R.drawable.cl_g6_11,
        R.string._12_no_information to R.drawable.cl_g6_12,
        R.string._13_good_for_fast_switch_to_f_6 to R.drawable.cl_g6_13,
        R.string._14_no_information to R.drawable.cl_g6_14,
        R.string._15_no_information to R.drawable.cl_g6_15,
        R.string._16_good_for_fast_switch_to_f_6 to R.drawable.cl_g6_16,
        R.string._17_good_for_fast_switch_to_e6_and_g_6 to R.drawable.cl_g6_17,
    ),
    "G#6" to mapOf(
        R.string._1_no_information to R.drawable.cl_gsharp6_1,
        R.string._2_no_information to R.drawable.cl_gsharp6_2,
        R.string._3_good_for_all_dynamics to R.drawable.cl_gsharp6_3,
        R.string._4_good_for_fast_switch_to_d_6 to R.drawable.cl_gsharp6_4,
        R.string._5_good_for_playing_very_loudly to R.drawable.cl_gsharp6_5,
        R.string._6_no_information to R.drawable.cl_gsharp6_6,
        R.string._7_no_information to R.drawable.cl_gsharp6_7,
        R.string._8_no_information to R.drawable.cl_gsharp6_8,
        R.string._9_no_information to R.drawable.cl_gsharp6_9,
        R.string._10_no_information to R.drawable.cl_gsharp6_10,
        R.string._11_no_information to R.drawable.cl_gsharp6_11,
        R.string._12_no_information to R.drawable.cl_gsharp6_12,
        R.string._13_no_information to R.drawable.cl_gsharp6_13,
        R.string._14_no_information to R.drawable.cl_gsharp6_14,
        R.string._15_no_information to R.drawable.cl_gsharp6_15,
        R.string._16_slightly_sharp to R.drawable.cl_gsharp6_16,
        R.string._17_no_information to R.drawable.cl_gsharp6_17,
        R.string._18_good_for_fast_switch_to_e6 to R.drawable.cl_gsharp6_18,
    ),
    "A6" to mapOf(
        R.string._1_no_information to R.drawable.cl_a6_1,
        R.string._2_no_information to R.drawable.cl_a6_2,
        R.string._3_no_information to R.drawable.cl_a6_3,
        R.string._4_no_information to R.drawable.cl_a6_4,
        R.string._5_no_information to R.drawable.cl_a6_5,
        R.string._6_no_information to R.drawable.cl_a6_6,
        R.string._7_no_information to R.drawable.cl_a6_7,
        R.string._8_no_information to R.drawable.cl_a6_8,
        R.string._9_no_information to R.drawable.cl_a6_9,
        R.string._10_no_information to R.drawable.cl_a6_10,
    ),
    "A#6" to mapOf(
        R.string._1_no_information to R.drawable.cl_asharp6_1,
        R.string._2_no_information to R.drawable.cl_asharp6_2,
        R.string._3_no_information to R.drawable.cl_asharp6_3,
        R.string._4_no_information to R.drawable.cl_asharp6_4,
        R.string._5_no_information to R.drawable.cl_asharp6_5,
        R.string._6_no_information to R.drawable.cl_asharp6_6,
        R.string._7_no_information to R.drawable.cl_asharp6_7,
        R.string._8_slightly_sharp to R.drawable.cl_asharp6_8,
        R.string._9_no_information to R.drawable.cl_asharp6_9,
        R.string._10_no_information to R.drawable.cl_asharp6_10,
        R.string._11_no_information to R.drawable.cl_asharp6_11,
        R.string._12_no_information to R.drawable.cl_asharp6_12,
        R.string._13_slightly_sharp to R.drawable.cl_asharp6_13,
    ),
    "B6" to mapOf(
        R.string._1_no_information to R.drawable.cl_b6_1,
        R.string._2_no_information to R.drawable.cl_b6_2,
        R.string._3_no_information to R.drawable.cl_b6_3,
        R.string._4_no_information to R.drawable.cl_b6_4,
        R.string._5_no_information to R.drawable.cl_b6_5,
        R.string._6_no_information to R.drawable.cl_b6_6,
        R.string._7_no_information to R.drawable.cl_b6_7,
    ),
    "C7" to mapOf(
        R.string._1_no_information to R.drawable.cl_c7_1,
        R.string._2_no_information to R.drawable.cl_c7_2,
        R.string._3_no_information to R.drawable.cl_c7_3,
        R.string._4_no_information to R.drawable.cl_c7_4,
        R.string._5_no_information to R.drawable.cl_c7_5,
        R.string._6_no_information to R.drawable.cl_c7_6,
        R.string._7_no_information to R.drawable.cl_c7_7,
        R.string._8_no_information to R.drawable.cl_c7_8,
        R.string._9_no_information to R.drawable.cl_c7_9,
        R.string._10_no_information to R.drawable.cl_c7_10,
        R.string._11_no_information to R.drawable.cl_c7_11,
        R.string._12_no_information to R.drawable.cl_c7_12,
        R.string._13_no_information to R.drawable.cl_c7_13,
    ),
    "C#7" to mapOf(
        R.string._1_no_information to R.drawable.cl_csharp7_1,
        R.string._2_no_information to R.drawable.cl_csharp7_2,
        R.string._3_no_information to R.drawable.cl_csharp7_3,
        R.string._4_no_information to R.drawable.cl_csharp7_4,
        R.string._5_no_information to R.drawable.cl_csharp7_5,
        R.string._6_no_information to R.drawable.cl_csharp7_6,
    ),
    "D7" to mapOf(
        R.string._1_no_information to R.drawable.cl_d7_1,
        R.string._2_no_information to R.drawable.cl_d7_2,
        R.string._3_no_information to R.drawable.cl_d7_3,
        R.string._4_no_information to R.drawable.cl_d7_4,
        R.string._5_no_information to R.drawable.cl_d7_5,
    ),
    "D#7" to mapOf(
        R.string._1_no_information to R.drawable.cl_dsharp7_1,
        R.string._2_no_information to R.drawable.cl_dsharp7_2,
    ),
    "E7" to mapOf(
        R.string._1_no_information to R.drawable.cl_e7_1,
        R.string._2_no_information to R.drawable.cl_e7_2,
        R.string._3_good_for_all_dynamics to R.drawable.cl_e7_3,
    ),
    "F7" to mapOf(
        R.string._1_no_information to R.drawable.cl_f7_1,
        R.string._2_no_information to R.drawable.cl_f7_2,
    ),
    "F#7" to mapOf(
        R.string._1_no_information to R.drawable.cl_fsharp7_1,
        R.string._2_no_information to R.drawable.cl_fsharp7_2,
    ),
    "G7" to mapOf(
        R.string._1_no_information to R.drawable.cl_g7_1,
        R.string._2_no_information to R.drawable.cl_g7_2,
    ),
    "G#7" to mapOf(
        R.string._1_no_information to R.drawable.cl_gsharp7_1,
    ),
    "A7" to mapOf(
        R.string._1_slightly_flat_good_for_playing_very_loudly to R.drawable.cl_a7_1,
        R.string._2_slightly_sharp_good_for_playing_very_softly to R.drawable.cl_a7_1,
    )
)

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.MainScreen.route) {
        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.ClarinetScreen.route
        ) {
            ClarinetScreen(navController = navController)
        }
        composable(
            route = Screen.BassClarinetScreen.route
        ) {
            BassClarinetScreen(navController = navController)
        }
        composable(
            route = Screen.FingeringScreen.route + "/{note}/{instrument}",
            arguments = listOf(
                navArgument("note") {
                    type = NavType.StringType
                    defaultValue = "C4"
                    nullable = false
                },
                navArgument("instrument") {
                    type = NavType.StringType
                    defaultValue = "Clarinet"
                    nullable = false
                },
            )
        ) {entry ->
            FingeringScreen(entry.arguments?.getString("note"), entry.arguments?.getString("instrument"), navController)
        }
    }
}


@Composable
fun MainScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = "Clarinet Fingerings",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "What instrument do you play?",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Screen.ClarinetScreen.route)
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth() // Center content within the Card
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.clarinet),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "B♭ Clarinet/E♭ Clarinet",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Screen.BassClarinetScreen.route)
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.bass_clarinet),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "B♭ Bass Clarinet/E♭ Contra-alto Clarinet/B♭ Contrabass Clarinet",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun ClarinetScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Clarinet Fingerings",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        for ((note, image) in clarinetRange) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate(
                            Screen.FingeringScreen.withArgs(note, "clarinet")
                        )
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(8.dp)
                            .clip(shape = RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = note,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Back")
        }
    }
}

@Composable
fun BassClarinetScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Bass Clarinet Fingerings",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        for ((note, image) in bassClarinetRange) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate(
                            Screen.FingeringScreen.withArgs(note, "bass clarinet")
                        )
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(8.dp)
                            .clip(shape = RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = note,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Back")
        }
    }
}

@Composable
fun FingeringScreen(note: String?, instrument: String?, navController: NavController) {
    val fingerings = when (instrument) {
        "clarinet" -> clarinetFingerings[note].orEmpty()
        "bass clarinet" -> BassClarinetFingerings[note].orEmpty()
        else -> { clarinetFingerings[note].orEmpty() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "${instrument?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }} Fingerings for $note",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        for ((descriptionResId, image) in fingerings) {
            val description = stringResource(id = descriptionResId)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(shape = RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = description,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            text = stringResource(R.string.to_report_an_error_or_submit_another_fingering_description_please_email_me_at_jerrycui07_gmail_com),
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(R.string.back))
        }
    }
}
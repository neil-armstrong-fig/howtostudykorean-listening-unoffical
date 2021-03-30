package com.howtostudykorean.downloading

import com.howtostudykorean.dagger.DaggerRule
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx2.await
import org.hamcrest.Matchers.hasSize
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class WebCrawlerTest {
    @Rule @JvmField val daggerRule = DaggerRule(this)

    @Inject lateinit var target: WebCrawler

    @Test
    fun shouldExtractLesson1() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_1).await()

        assertThat(audioTracks, hasSize(51))
        assertThatHasItemWithName(audioTracks, "그 사람은 의사야 / 그 사람은 의사예요")
    }

    @Test
    fun shouldExtractLesson2() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_2).await()

        assertThat(audioTracks, hasSize(46))
    }

    @Test
    fun shouldExtractLesson3() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_3).await()

        assertThat(audioTracks, hasSize(68))
    }

    @Test
    fun shouldExtractLesson4() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_4).await()

        assertThat(audioTracks, hasSize(84))
    }

    @Test
    fun shouldExtractLesson5() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_5).await()

        assertThat(audioTracks, hasSize(87))
    }

    @Test
    fun shouldExtractLesson6() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_6).await()

        assertThat(audioTracks, hasSize(72))
    }

    @Test
    fun shouldExtractLesson7() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_7).await()

        assertThat(audioTracks, hasSize(93))
    }

    @Test
    fun shouldExtractLesson8() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_8).await()

        assertThat(audioTracks, hasSize(104))
    }

    @Test
    fun shouldExtractLesson9() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_9).await()

        assertThat(audioTracks, hasSize(109))
        assertThatHasItemWithName(audioTracks, "축구하다")
        assertThatHasItemWithName(audioTracks, "야구하다")
    }

    @Test
    fun shouldExtractLesson10() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_10).await()

        assertThat(audioTracks, hasSize(100))
    }

    @Test
    fun shouldExtractLesson11() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_11).await()

        assertThat(audioTracks, hasSize(122))
        assertThatHasItemWithName(audioTracks, "평생 동안")
    }

    @Test
    fun shouldExtractLesson12() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_12).await()

        assertThat(audioTracks, hasSize(118))
        assertThatHasItemWithName(audioTracks, "배우들은 그들의 영화를 보통 좋아하지 않아")
    }

    @Test
    fun shouldExtractLesson13() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_13).await()

        assertThat(audioTracks, hasSize(86))
        assertThatHasItemWithName(audioTracks, "나는 나의 여자 친구를 위해서 꽃을 샀어")
        assertThatHasItemWithName(audioTracks, "나는 부장님을 위해 이것을 썼어")
    }

    @Test
    fun shouldExtractLesson14() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_14).await()

        assertThat(audioTracks, hasSize(97))
        assertThatHasItemWithName(audioTracks, "냄새나다")
        assertThatHasItemWithName(audioTracks, "나는 너의 말에 감동받았어")
        assertThatHasItemWithName(audioTracks, "아! 그것이 기억났다!")
        assertThatHasItemWithName(audioTracks, "좋은 생각이 났어요")
        assertThatHasItemWithName(audioTracks, "세금은 값에 포함된다")
    }

    @Test
    fun shouldExtractLesson15() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_15).await()

        assertThat(audioTracks, hasSize(110))
        assertThatHasItemWithName(audioTracks, "나는 너의 남자친구와 비슷한 남자를 만났어")
    }

    @Test
    fun shouldExtractLesson16() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_16).await()

        assertThat(audioTracks, hasSize(99))
        assertThatHasItemWithName(audioTracks, "민주주의")
        assertThatHasItemWithName(audioTracks, "실망하다")
        assertThatHasItemWithName(audioTracks, "사랑하다")
        assertThatHasItemWithName(audioTracks, "만족하다")
    }

    @Test
    fun shouldExtractLesson17() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_17).await()

        assertThat(audioTracks, hasSize(90))
        assertThatHasItemWithName(audioTracks, "그 여자는 나랑 결혼하고 싶었어")
    }

    @Test
    fun shouldExtractLesson18() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_18).await()

        assertThat(audioTracks, hasSize(83))
        assertThatHasItemWithName(audioTracks, "집에 빨리 와! 알았어")
        assertThatHasItemWithName(audioTracks, "우리는 내일 6시에 출발 할 거야. 알았어")
        assertThatHasItemWithName(audioTracks, "이 일을 내일까지 해야 합니다. 네, 알겠습니다")
    }

    @Test
    fun shouldExtractLesson19() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_19).await()

        assertThat(audioTracks, hasSize(125))
    }

    @Test
    fun shouldExtractLesson20() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_20).await()

        assertThat(audioTracks, hasSize(64))
    }

    @Test
    fun shouldExtractLesson21() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_21).await()

        assertThat(audioTracks, hasSize(101))
        assertThatHasItemWithName(audioTracks, "낮잠자다")
        assertThatHasItemWithName(audioTracks, "슬기야! 왜?")
        assertThatHasItemWithName(audioTracks, "너는 내일 누구 만날 거야?")
    }

    @Test
    fun shouldExtractLesson22() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_22).await()

        assertThat(audioTracks, hasSize(112))
        assertThatHasItemWithName(audioTracks, "이 사진이 어때?")
        assertThatHasItemWithName(audioTracks, "무슨 생각 해?")
        assertThatHasItemWithName(audioTracks, "슬기야! 왜?")
        assertThatHasItemWithName(audioTracks, "저는 많이 먹었어요. 뭐를?")
    }

    @Test
    fun shouldExtractLesson23() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_23).await()

        assertThat(audioTracks, hasSize(102))
        assertThatHasItemWithName(audioTracks, "저는 초록색 펜으로 쓰고 싶어요")
        assertThatHasItemWithName(audioTracks, "연두색 바지를 샀어요")
        assertThatHasItemWithName(audioTracks, "대부분의 여자들은 분홍색 가방을 골랐어요")
        assertThatHasItemWithName(audioTracks, "남자 친구가 보라색 꽃 한 송이를 샀어요")
        assertThatHasItemWithName(audioTracks, "이 학교도 그렇지 않습니까? 네, 그렇습니다")
        assertThatHasItemWithName(audioTracks, "왜 이래? 왜 그래? 왜 저래?")
        assertThatHasItemWithName(audioTracks, "내일 공원에 같이 가고 싶어요? 그래요. 같이 가요")
        assertThatHasItemWithName(audioTracks, "제가 지금 갈 거예요. 그래요!")
        assertThatHasItemWithName(audioTracks, "저는 내일 회사에 못 와요. 그래요! 월요일에 봐요!")
        assertThatHasItemWithName(audioTracks, "저는 지난 주에 캐나다에 있었어요. 그래요? 어디에 갔어요?")
        assertThatHasItemWithName(audioTracks, "나는 보통 고기를 안 먹어. 그래? 왜 안 먹어?")
        assertThatHasItemWithName(audioTracks, "이 물이 맛이 없어. 그래?")
    }

    @Test
    fun shouldExtractLesson24() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_24).await()

        assertThat(audioTracks, hasSize(73))
    }

    @Test
    fun shouldExtractLesson25() = runBlocking {
        val audioTracks = target.extractAudioFromUrl(Page.LESSON_25).await()

        assertThat(audioTracks, hasSize(138))
        assertThatHasItemWithName(audioTracks, "누군가 너를 찾고 있어")
        assertThatHasItemWithName(audioTracks, "나는 방금 뭔가 봤어")
        assertThatHasItemWithName(audioTracks, "등에 뭔가 있어요")
        assertThatHasItemWithName(audioTracks, "등에 뭔가 있나요?")
        assertThatHasItemWithName(audioTracks, "저는 팔에 뭔가느껴져요")
        assertThatHasItemWithName(audioTracks, "저는 뭔가 먹고 싶어요")
        assertThatHasItemWithName(audioTracks, "저는 뭔가 말하고 싶어요")
        assertThatHasItemWithName(audioTracks, "열쇠를 어딘가 뒀어")
        assertThatHasItemWithName(audioTracks, "저는 그릇을 어딘가 두었어요")
        assertThatHasItemWithName(audioTracks, "저는 그릇을 어딘가 두었어요")
    }

    private fun assertThatHasItemWithName(audioTracks: List<AudioTrack>, name: String) {
        val audioTrack = audioTracks.firstOrNull { it.audioText == name }
        if (audioTrack == null) {
            val tracksAsString = audioTracks.mapIndexed { index, audioTrack ->
                "$index. ${audioTrack.audioText}\n"
            }.joinToString("")
            throw AssertionError("Failed to find track '$name', list of tracks:\n$tracksAsString")
        }
    }
}
package com.howtostudykorean.downloading

import org.jsoup.nodes.Element
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonTextConverter @Inject constructor() {
    fun toAudioTrack(element: Element, page: Page) = when (page) {
        Page.LESSON_1 -> parseLesson1(element)
        Page.LESSON_9 -> parseLesson9(element)
        Page.LESSON_11 -> parseLesson11(element)
        Page.LESSON_12 -> parseLesson12(element)
        Page.LESSON_13 -> parseLesson13(element)
        Page.LESSON_14 -> parseLesson14(element)
        Page.LESSON_15 -> parseLesson15(element)
        Page.LESSON_16 -> parseLesson16(element)
        Page.LESSON_17 -> parseLesson17(element)
        Page.LESSON_18 -> parseLesson18(element)
        Page.LESSON_21 -> parseLesson21(element)
        Page.LESSON_22 -> parseLesson22(element)
        Page.LESSON_23 -> parseLesson23(element)
        Page.LESSON_25 -> parseLesson25(element)
        else -> createDefaultAudioTrack(element)
    }

    private fun parseLesson1(element: Element) = when (element.text()) {
        "(그 사람은 의사야 / 그 사람은 의사예요)" -> AudioTrack("그 사람은 의사야 / 그 사람은 의사예요", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson9(element: Element) = when (element.text()) {
        "축구(하다)" -> AudioTrack("축구하다", element.attr("href"))
        "야구(하다)" -> AudioTrack("야구하다", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson11(element: Element) = when (element.text()) {
        "평생 (동안)" -> AudioTrack("평생 동안", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson12(element: Element) = when (element.text()) {
        "배우들은 그들의* 영화를 보통 좋아하지 않아" -> AudioTrack("배우들은 그들의 영화를 보통 좋아하지 않아", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson13(element: Element) = when (element.text()) {
        "나는 나의 여자 친구를 위해(서) 꽃을 샀어" -> AudioTrack("나는 나의 여자 친구를 위해서 꽃을 샀어", element.attr("href"))
        "나는 부장님을 위해(서) 이것을 썼어" -> AudioTrack("나는 부장님을 위해 이것을 썼어", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson14(element: Element) = when (element.text()) {
        "냄새 (나다)" -> AudioTrack("냄새나다", element.attr("href"))
        "나는 너의 말에* 감동받았어" -> null
        "아! 그것이* 기억났다!" -> null
        "좋은 생각이* 났어요!" -> AudioTrack("좋은 생각이 났어요", element.attr("href"))
        "은 값에 포함된다" -> AudioTrack("세금은 값에 포함된다", element.attr("href"))
        "집은 청소기로 청소되었어요" -> AudioTrack("집은 청소기로 청소되었어", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson15(element: Element) = when (element.text()) {
        "나는 (너의 남자친구와 비슷한 남자)를 만났어" -> AudioTrack("나는 너의 남자친구와 비슷한 남자를 만났어", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson16(element: Element) = when (element.text()) {
        "민주(주의)" -> AudioTrack("민주주의", element.attr("href"))
        "실망(하다)" -> AudioTrack("실망하다", element.attr("href"))
        "사랑(하다)" -> AudioTrack("사랑하다", element.attr("href"))
        "만족(하다)" -> AudioTrack("만족하다", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson17(element: Element) = when (element.text()) {
        "혜원도 오고… 슬기도 오고… 승하도 오고… 지혜도 오고…" -> null
        "그 여자는 나랑* 결혼하고 싶었어" -> AudioTrack("그 여자는 나랑 결혼하고 싶었어", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson18(element: Element) = when (element.text()) {
        "집에 빨리 와!" -> AudioTrack("집에 빨리 와! 알았어", element.attr("href"))
        "우리는 내일 6시에 출발 할 거야" -> AudioTrack("우리는 내일 6시에 출발 할 거야. 알았어", element.attr("href"))
        "이 일을 내일까지 해야 합니다" -> AudioTrack("이 일을 내일까지 해야 합니다. 네, 알겠습니다", element.attr("href"))
        "알았어" -> null
        "네, 알겠습니다" -> null
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson21(element: Element) = when (element.text()) {
        "낮잠 (자다)" -> AudioTrack("낮잠자다", element.attr("href"))
        "슬기야!" -> AudioTrack("슬기야! 왜?", element.attr("href"))
        "왜?" -> null
        "너는 내일 누구(를) 만날 거야?" -> AudioTrack("너는 내일 누구 만날 거야?", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson22(element: Element) = when (element.text()) {
        "이 사진(이) 어때?" -> AudioTrack("이 사진이 어때?", element.attr("href"))
        "무슨 생각(을) 해?" -> AudioTrack("무슨 생각 해?", element.attr("href"))
        "슬기야!" -> AudioTrack("슬기야! 왜?", element.attr("href"))
        "저는 많이 먹었어요" -> AudioTrack("저는 많이 먹었어요. 뭐를?", element.attr("href"))
        "왜?" -> null
        "뭐(를)?" -> null
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson23(element: Element) = when (element.text()) {
        "저는 초록색(의) 펜으로 쓰고 싶어요" -> AudioTrack("저는 초록색 펜으로 쓰고 싶어요", element.attr("href"))
        "연두색(의) 바지를 샀어요" -> AudioTrack("연두색 바지를 샀어요", element.attr("href"))
        "대부분(의) 여자들은 분홍색(의) 가방을 골랐어요" -> AudioTrack("대부분의 여자들은 분홍색 가방을 골랐어요", element.attr("href"))
        "남자 친구가 보라색(의) 꽃 한 송이를 샀어요" -> AudioTrack("남자 친구가 보라색 꽃 한 송이를 샀어요", element.attr("href"))
        "이 학교도 그렇지 않습니까?" -> AudioTrack("이 학교도 그렇지 않습니까? 네, 그렇습니다", element.attr("href"))
        "왜 이래?" -> AudioTrack("왜 이래? 왜 그래? 왜 저래?", element.attr("href"))
        "내일 공원에 같이 가고 싶어요?" -> AudioTrack("내일 공원에 같이 가고 싶어요? 그래요. 같이 가요", element.attr("href"))
        "제가 지금 갈 거예요" -> AudioTrack("제가 지금 갈 거예요. 그래요!", element.attr("href"))
        "저는 내일 회사에 못 와요" -> AudioTrack("저는 내일 회사에 못 와요. 그래요! 월요일에 봐요!", element.attr("href"))
        "저는 지난 주에 캐나다에 있었어요" -> AudioTrack("저는 지난 주에 캐나다에 있었어요. 그래요? 어디에 갔어요?", element.attr("href"))
        "나는 보통 고기를 안 먹어" -> AudioTrack("나는 보통 고기를 안 먹어. 그래? 왜 안 먹어?", element.attr("href"))
        "이 물이 맛이 없어" -> AudioTrack("이 물이 맛이 없어. 그래?", element.attr("href"))
        "네, 그렇습니다" -> null
        "왜 그래?" -> null
        "왜 저래?" -> null
        "그래요. 같이 가요" -> null
        "그래요!" -> null
        "그래요! 월요일에 봐요!" -> null
        "그래요? 어디에 갔어요?" -> null
        "그래? 왜 안 먹어?" -> null
        "그래?" -> null
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun parseLesson25(element: Element) = when (element.text()) {
        "누군가(는) 너를 찾고 있어" -> AudioTrack("누군가 너를 찾고 있어", element.attr("href"))
        "나는 방금 뭔가(를) 봤어" -> AudioTrack("나는 방금 뭔가 봤어", element.attr("href"))
        "등에 뭔가(가) 있어요" -> AudioTrack("등에 뭔가 있어요", element.attr("href"))
        "등에 뭔가(가) 있나요?" -> AudioTrack("등에 뭔가 있나요?", element.attr("href"))
        "저는 팔에 뭔가(가)느껴져요" -> AudioTrack("저는 팔에 뭔가느껴져요", element.attr("href"))
        "저는 뭔가(를) 먹고 싶어요" -> AudioTrack("저는 뭔가 먹고 싶어요", element.attr("href"))
        "저는 뭔가(를) 말하고 싶어요" -> AudioTrack("저는 뭔가 말하고 싶어요", element.attr("href"))
        "열쇠를 어딘가(에) 뒀어" -> AudioTrack("열쇠를 어딘가 뒀어", element.attr("href"))
        "저는 그릇을 어딘가(에) 두었어요" -> AudioTrack("저는 그릇을 어딘가 두었어요", element.attr("href"))
        else -> AudioTrack(element.text(), element.attr("href"))
    }

    private fun createDefaultAudioTrack(element: Element) = AudioTrack(element.text(), element.attr("href"))
}
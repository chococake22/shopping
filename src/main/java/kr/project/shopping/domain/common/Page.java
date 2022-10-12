package kr.project.shopping.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    private final int recordSize = 10;   // 페이지당 보여줄 글 개수    -> 나중에 properties로 옮겨서 @Value 사용
    private final int pageSize = 5;    // 한 화면에 보여줄 페이지 번호 개수     // -> 나중에 properties로 옮겨서 @Value 사용
    private int startIndex; // 시작 글 번호
    private int endIndex;   // 마지막 글 번호

    // 페이지
    private int nowPage;    // 현재 페이지

    // 블록
    private int startPage;  // 시작 페이지 번호
    private int endPage;    // 마지막 페이지 번호

    // 완전 맨 끝 페이지 번호
    private int lastPageNum;

    private int totalPages;

    private int offset; // 시작 번호 ex) 20이면 20번째 부터
    private int totalCount; // 전체 글 개수

    private boolean prev, next;

    // 검색 관련
    private String keyword;
    private String startDate;
    private String endDate;


    public Page(int nowPage, int totalCount) {
        this.endPage = (int) (Math.ceil(nowPage / 10.0) * 10);
        this.startPage = endPage - 9;
        this.lastPageNum = (int) Math.ceil((totalCount * 1.0) / recordSize);

        if(lastPageNum < this.endPage) {
            this.endPage = lastPageNum;
        }

        this.nowPage = nowPage;
        this.prev = this.nowPage > 1;
        this.next = this.nowPage < lastPageNum;

        if (nowPage == 0) {
            this.offset = 0;
        }

        this.offset = ((nowPage - 1) * recordSize);
    }

    // 계산 로직이 필요하다.

    // 구간의 끝 페이지 번호
    public int getEndPage(int nowPage) {
        endPage = (int) Math.ceil(nowPage / 10) * 10;
        return endPage;
    }

    // 구간의 시작 페이지 번호
    public int getStartPage(int endPage) {
        startPage = endPage - 9;
        return startPage;
    }

    public int getLastPage() {
        lastPageNum = (int) (Math.ceil(totalCount * 1.0) / recordSize) + 1;
        if (lastPageNum < this.endPage) {
            this.endPage = lastPageNum;
        }

        return lastPageNum;
    }
}

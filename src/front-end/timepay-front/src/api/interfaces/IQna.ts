import { PageableData } from './ICommon';

export interface IQna {
  inquiryId: number; // 문의 번호
  title: string; // 제목
  createdAt?: string; // 작성 날짜
  state: IQnaState; // 게시글 상태
  category: string; // 게시글 카테고리
  content: string; // 내용
  attachment?: string; // 첨부파일
}

export interface IPostQna {
  content: string;
  state: string;
  title: string;
  category?: string;
}

export type IQnaState = '답변대기' | '답변완료';

export interface IGetQna extends PageableData {
  content: IQna[];
}

import { PageableData } from './ICommon';
import { IUser } from './IUser';

export interface IPost {
  postId: number; // 게시글 번호
  type: IPostType; // 게시글 유형
  createdAt: string; // 작성 날짜
  updatedAt?: string; // 수정 날짜
  title: string; // 제목
  status: IPostState; // 게시글 상태
  category: string; // 게시글 카테고리
  pay: number; // 타임페이 교환량
  startTime: string; //활동 시작 시간
  endTime: string; // 종료 시간
  region: string; // 지역
  attachment?: string; // 첨부파일
  user: IUser; // 작성자 정보
  content: string; // 게시글 내용
}

export interface IPostFreeBoard {
  title: string; // 제목
  content: string;
  category: string;
  hidden: boolean;
}
export interface IGetFreeBoard {
  title: string; // 제목
  content: string;
  category: string;
  createdAt: string;
  updatedAt?: string;
  hidden?: boolean;
}

export interface IPostDealBoard {
  title: string;
  category: string;
  content: string;
  location: string;
  state?: string;
  starttime?: string;
  endtime?: string;
  hidden?: boolean;
  pay?: 100;
  imageUrl?: string;
}

export interface IGetDealBoard {
  title: string;
  content: string;
  location: string;
  state: string;
  starttime?: string;
  endtime?: string;
  hidden?: boolean;
  pay?: 100;
}

export type IPostState =
  | '매칭중'
  | '매칭완료'
  | '활동중'
  | '활동완료'
  | '활동지연'
  | '활동취소';

export type IPostType = '도움요청' | '같이하기' | '기부하기';

export interface IBoard {
  createdAt: string;
  updatedAt: string;
  d_boardId: number;
  title: string;
  content: string;
  type: string;
  category?: string;
  location?: string;
  startTime: any;
  endTime: any;
  pay: number;
  boardStatus: string;
  state: any;
  volunteerTime: number;
  volunteerPeople: number;
  hidden: boolean;
  auto: boolean;
  volunteer: boolean;
  writerName?: string;
  writerNickname?: string;
  writerType?: string;
  imageUrl?: string;
}
export interface IGetSearchBoardRequest {
  title?: string;
  type?: string;
  category?: string;
  sortType?: string;
  startDate: null; // 프론트에서만 사용
  startTime?: string;
  endDate: null; // 프론트에서만 사용
  endTime?: string;
  volunteer?: boolean;
  pagingSize: number;
  pagingIndex: number;
}

export interface IGetSearchBoardResponse extends PageableData {
  content: IBoard[];
}

export interface IVolunteerBoard {
  boardId: number;
  title: string;
  organizationName: string;
  managerName: string;
  managerPhone: string;
  volunteerTime: number;
  state: boolean;
  certificationUrl?: string | null;
}

export interface IVolunteerBoardInfo {
  title: string;
  location: string;
  startTime: string;
  endTime: string;
  participateNum: number;
}

export interface IVolunteerUser {
  userId: number;
  userName: string;
  userNickname: string;
  email: string;
  phone: string;
  certificationUrl?: string | null;
  published: boolean;
}
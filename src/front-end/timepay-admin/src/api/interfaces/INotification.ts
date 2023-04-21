import { GetPageableData, PageableData } from './ICommon';

export interface INotification {
  createdAt: string;
  updatedAt: string;
  notificationId: number;
  title: string;
  imageUrl: any;
  content: string;
  state: any;
  adminName: string;
  notice: boolean;
}

export interface IPostNotificationRequest {
  content: string;
  imageUrl: string;
  notice: boolean;
  title: string;
}
export interface IGetNotificationRequest extends GetPageableData {
  title?: string;
}
export interface IGetNotificationResponse extends PageableData {
  content: INotification[];
}

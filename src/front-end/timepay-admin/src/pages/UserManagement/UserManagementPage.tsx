import {
  Card,
  Space,
  Select,
  Input,
  Button,
  Table,
  Typography,
  Modal,
  Image,
} from 'antd';
import React from 'react';
import { useState } from 'react';
import { css } from '@emotion/react';
import type { ColumnsType } from 'antd/es/table';
import { VerticalAlignBottomOutlined } from '@ant-design/icons';

/*수직 수평 중앙 정렬*/
const topWrapperCSS = css`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 110vh;
  overflow: scroll;
`;

/* 수평 오른쪽 정렬 */
const rightAlignCSS = css`
  display: flex;
  justify-content: right;
  margin-top: 15px;
`;

const UserManagementPage = () => {
  /*프로필 사진 모달 설정 */
  const [modalProfileImage, setModalProfileImage] = useState(false);

  const showModalProfileImage = () => {
    setModalProfileImage(true);
  };

  const handleOkProfileImage = () => {
    setModalProfileImage(false);
  };

  const handleCancelProfileImage = () => {
    setModalProfileImage(false);
  };

  /*회원 정보 수정 모달 설정 */
  const [modalEdit, setModalEdit] = useState(false);

  const showModalEdit = () => {
    setModalEdit(true);
  };

  const handleOkEdit = () => {
    setModalEdit(false);
  };

  const handleCancelEdit = () => {
    setModalEdit(false);
  };

  /*회원 활동 목록 모달 설정 */
  const [modalDetail, setModalDetail] = useState(false);

  const showModalDetail = () => {
    setModalDetail(true);
  };

  const handleOkDetail = () => {
    setModalDetail(false);
  };

  const handleCancelDetail = () => {
    setModalDetail(false);
  };

  /*회원 활동 목록 탭 설정 */
  const tabList = [
    {
      key: '게시글',
      tab: '게시글',
    },
    {
      key: '댓글',
      tab: '댓글',
    },
    {
      key: '신고받은내역',
      tab: '신고받은 내역',
    },
    {
      key: '신고한내역',
      tab: '신고한 내역',
    },
  ];

  /*회원 활동 목록 게시글 탭 Table Data 설정 */
  interface DataTypeUserPost {
    key: React.Key;
    postNum: number;
    postTitle: string;
    writeDate: string;
    type: string;
    curState: string;
    setActivityTime: string;
    realActivityTime: string;
  }

  const columnsUserPost: ColumnsType<DataTypeUserPost> = [
    {
      title: '게시글 번호',
      dataIndex: 'postNum',
      align: 'center',
    },
    {
      title: '제목',
      dataIndex: 'postTitle',
      align: 'center',
    },
    {
      title: '작성 날짜',
      dataIndex: 'writeDate',
      align: 'center',
    },
    {
      title: '유형',
      dataIndex: 'type',
      align: 'center',
    },
    {
      title: '현재 상태',
      dataIndex: 'curState',
      align: 'center',
    },
    {
      title: '설정 활동 시간',
      dataIndex: 'setActivityTime',
      align: 'center',
    },
    {
      title: '실제 활동 시간',
      dataIndex: 'realActivityTime',
      align: 'center',
    },
  ];

  const dataUserPost: DataTypeUserPost[] = [];
  for (let i = 1; i < 12; i++) {
    dataUserPost.push({
      key: i,
      postNum: i,
      postTitle: `제목 ${i}`,
      writeDate: `2023-01-0${i}`,
      type: `도움 주기`,
      curState: `활동 완료`,
      setActivityTime: `0${i}:0${i * 3}~0${i + 2}:0${i}`,
      realActivityTime: `0${i}:0${i * 4}~0${i + 3}:0${i}`,
    });
  }

  /*회원 활동 목록 댓글 탭 Table Data 설정 */
  interface DataTypeUserComment {
    key: React.Key;
    commentNum: number;
    writeDate: string;
    apply: string;
    decide: string;
    content: string;
  }

  const columnsUserComment: ColumnsType<DataTypeUserComment> = [
    {
      title: '댓글 번호',
      dataIndex: 'commentNum',
      align: 'center',
    },
    {
      title: '작성 날짜',
      dataIndex: 'writeDate',
      align: 'center',
    },

    {
      title: '지원 여부',
      dataIndex: 'apply',
      align: 'center',
    },

    {
      title: '확정 여부',
      dataIndex: 'decide',
      align: 'center',
    },

    {
      title: '내용',
      dataIndex: 'content',
      align: 'center',
    },
  ];

  const dataUserComment: DataTypeUserComment[] = [];
  for (let i = 1; i < 11; i++) {
    dataUserComment.push({
      key: i,
      commentNum: i,
      writeDate: `2023-03-0${i}`,
      apply: `O`,
      decide: `X`,
      content: `어떤 언어로 수업하시나요?`,
    });
  }

  /*회원 활동 목록 신고 받은 내역 탭 Table Data 설정 */
  interface DataTypeUserReceiveReport {
    key: React.Key;
    reportNum: number;
    nickNameReporter: string;
    reason: string;
    postNum: number;
    commentNum: number;
  }

  const columnsUserReceiveReport: ColumnsType<DataTypeUserReceiveReport> = [
    {
      title: '신고 번호',
      dataIndex: 'reportNum',
      align: 'center',
    },
    {
      title: '신고자',
      dataIndex: 'nickNameReporter',
      align: 'center',
    },
    {
      title: '신고 사유',
      dataIndex: 'reason',
      align: 'center',
    },
    {
      title: '게시글 번호',
      dataIndex: 'postNum',
      align: 'center',
    },
    {
      title: '댓글 번호',
      dataIndex: 'commentNum',
      align: 'center',
    },
  ];

  const dataUserReceiveReport: DataTypeUserReceiveReport[] = [];
  for (let i = 1; i < 11; i++) {
    dataUserReceiveReport.push({
      key: i,
      reportNum: i,
      nickNameReporter: `reporter ${i}`,
      reason: `신고 사유 ${i}`,
      postNum: i * 1231,
      commentNum: i * 362,
    });
  }

  /*회원 활동 목록 신고한 내역 탭 Table Data 설정 */
  interface DataTypeUserSendReport {
    key: React.Key;
    reportNum: number;
    nickNameReceiver: string;
    reason: string;
    postNum: number;
    commentNum: number;
  }

  const columnsUserSendReport: ColumnsType<DataTypeUserSendReport> = [
    {
      title: '신고 번호',
      dataIndex: 'reportNum',
      align: 'center',
    },
    {
      title: '신고 받은 사람',
      dataIndex: 'nickNameReceiver',
      align: 'center',
    },
    {
      title: '신고 사유',
      dataIndex: 'reason',
      align: 'center',
    },
    {
      title: '게시글 번호',
      dataIndex: 'postNum',
      align: 'center',
    },
    {
      title: '댓글 번호',
      dataIndex: 'commentNum',
      align: 'center',
    },
  ];

  const dataUserSendReport: DataTypeUserSendReport[] = [];
  for (let i = 1; i < 11; i++) {
    dataUserSendReport.push({
      key: i,
      reportNum: i,
      nickNameReceiver: `receiver ${i}`,
      reason: `신고 사유 ${i}`,
      postNum: i * 1231,
      commentNum: i * 362,
    });
  }

  const contentList: Record<string, React.ReactNode> = {
    게시글: <Table columns={columnsUserPost} dataSource={dataUserPost} />,
    댓글: <Table columns={columnsUserComment} dataSource={dataUserComment} />,
    신고받은내역: (
      <Table
        columns={columnsUserReceiveReport}
        dataSource={dataUserReceiveReport}
      />
    ),
    신고한내역: (
      <Table columns={columnsUserSendReport} dataSource={dataUserSendReport} />
    ),
  };

  const [activeTabKey, setActiveTabKey] = useState<string>('');

  const onTabChange = (key: string) => {
    setActiveTabKey(key);
  };

  /*기본 회원 조회 화면 Table Data 설정 */
  interface DataType {
    key: React.Key;

    nickName: string;
    realName: string;
    town: string;
    birth: string;
    profileImg: any;
    timePay: number;

    blackList: string;
    detail: any;
    edit: any;
  }

  const columns: ColumnsType<DataType> = [
    {
      title: '이름',
      dataIndex: 'nickName',
      align: 'center',
    },
    {
      title: '닉네임',
      dataIndex: 'realName',
      align: 'center',
    },
    {
      title: '지역',
      dataIndex: 'town',
      align: 'center',
    },
    {
      title: '생년월일',
      dataIndex: 'birth',
      align: 'center',
    },
    {
      title: '프로필 사진',
      dataIndex: 'profileImg',
      align: 'center',
    },
    {
      title: '타임페이 보유량',
      dataIndex: 'timePay',
      align: 'center',
    },
    {
      title: '활동 목록',
      dataIndex: 'detail',
      align: 'center',
    },
    {
      title: '블랙리스트 여부',
      dataIndex: 'blackList',
      align: 'center',
    },

    {
      title: '정보 수정',
      dataIndex: 'edit',
      align: 'center',
    },
  ];

  const data: DataType[] = [];
  for (let i = 0; i < 46; i++) {
    data.push({
      key: i,

      nickName: `nickname ${i}`,
      realName: `realname ${i}`,
      town: `서울특별시 ${i}구 ${i}동`,
      birth: `0000-00-00`,
      profileImg: <Button onClick={showModalProfileImage}>사진 보기</Button>,
      timePay: i * 100 + i,

      blackList: `X`,
      detail: <Button onClick={showModalDetail}>활동 보기</Button>,
      edit: <Button onClick={showModalEdit}>수정</Button>,
    });
  }

  /*블랙리스트 등록 모달 Table Data 설정 */
  interface DataTypeBlackList {
    key: React.Key;
    uid: number;
    realName: string;
    timePay: number;
  }

  const columnsBlackList: ColumnsType<DataTypeBlackList> = [
    {
      title: '회원 번호',
      dataIndex: 'uid',
      align: 'center',
    },
    {
      title: '이름',
      dataIndex: 'realName',
      align: 'center',
    },

    {
      title: '타임페이 보유량',
      dataIndex: 'timePay',
      align: 'center',
    },
  ];

  const dataBlackList: DataTypeBlackList[] = [];
  /*
  for (let i = 0; i < 5; i++) {
    dataBlackList.push({
      key: i,
      uid: i * 12 + i,
      realName: `realname ${i}`,

      timePay: i * 100 + i,
    });
  }*/

  /*회원 삭제 모달 Table Data 설정 */
  interface DataTypeDelUser {
    key: React.Key;
    uid: number;
    realName: string;
    timePay: number;
  }

  const columnsDelUser: ColumnsType<DataTypeDelUser> = [
    {
      title: '회원 번호',
      dataIndex: 'uid',
      align: 'center',
    },
    {
      title: '이름',
      dataIndex: 'realName',
      align: 'center',
    },

    {
      title: '타임페이 보유량',
      dataIndex: 'timePay',
      align: 'center',
    },
  ];

  const dataDelUser: DataTypeDelUser[] = [];
  for (let i = 0; i < 3; i++) {
    dataDelUser.push({
      key: i,
      uid: i * 12 + i,
      realName: `realname ${i}`,

      timePay: i * 100 + i,
    });
  }

  const { Text } = Typography;
  const [filter, setFilter] = useState<string>();
  const [modalBlackList, setModalBlackList] = useState(false);
  const [modalDelUser, setModalDelUser] = useState(false);
  const [searchFilter, setSearchFilter] = useState<string>();
  const [userNickName, setUserNickName] = useState<string>();
  const [userRealName, setUserRealName] = useState<string>();

  /*블랙리스트 등록 모달 handle 함수*/
  const showModalBlackList = () => {
    setModalBlackList(true);

    //체크된 회원 데이터 파싱 후, 모달에 띄우기
    dataBlackList
      .push( {  dataBlackList.push({}}/*{
      key: i,
      uid: i * 12 + i,
      realName: `realname ${i}`,

      timePay: i * 100 + i,
    }*/
    // data[selectedRowKeys[]]
  
      //data.map((user)=>)
      )
      )
  };

  const handleOkBlackList = () => {
    setModalBlackList(false);
  };

  const handleCancelBlackList = () => {
    setModalBlackList(false);
  };

  /*회원 삭제 모달 handle 함수*/
  const showModalDelUser = () => {
    setModalDelUser(true);
  };

  const handleOkDelUser = () => {
    setModalDelUser(false);
  };

  const handleCancelDelUser = () => {
    setModalDelUser(false);
  };

  /*Table */
  const [selectedRowKeys, setSelectedRowKeys] = useState<React.Key[]>([]);

  const onSelectChange = (newSelectedRowKeys: React.Key[]) => {
    console.log('selectedRowKeys changed: ', newSelectedRowKeys);
    setSelectedRowKeys(newSelectedRowKeys);
  };

  const rowSelection = {
    selectedRowKeys,
    onChange: onSelectChange,
  };
  const hasSelected = selectedRowKeys.length > 0;

  /*필터 검색*/
  const handleSearchBtn = () => {
    //이름과 닉네임으로 사용자 검색
  };

  const onChangeSearchRealName = (value: any) => {
    setUserRealName(value);
  };

  const onChangeSearchNickName = (value: any) => {
    setUserNickName(value);
  };

  /*회원 정보 수정 모달 onChange 함수*/
  const [editRealName, setEditRealName] = useState('');
  const [editNickName, setEditNickName] = useState('');
  const [editTown, setEditTown] = useState('');
  const [editBitrh, setEdiBirh] = useState('');

  const onChangeEditNickName = (value: any) => {};
  const onChangeEditRealName = (value: any) => {};
  const onChangeEditTown = (value: any) => {};
  const onChangeEditBirth = (value: any) => {};

  /*메인에서 체크된 row들 데이터 파싱해서 블랙리스트로 넘기기 */

  return (
    <div>
      <Space direction="vertical" css={topWrapperCSS}>
        <Card title="회원 관리" style={{ width: 1630, height: 970 }}>
          <Card style={{ textAlign: 'center' }}>
            <Text strong>이름</Text>
            <Input
              css={{ width: 170, marginLeft: 20 }}
              onChange={onChangeSearchRealName}
            />
            <Text strong style={{ marginLeft: 90 }}>
              닉네임
            </Text>
            <Input
              css={{ width: 170, marginLeft: 20 }}
              onChange={onChangeSearchNickName}
            />
            <Button
              css={css`
                background: gray;
              `}
              style={{ width: 80, marginLeft: 70 }}
              type="primary"
              onClick={handleSearchBtn}
            >
              검색
            </Button>
          </Card>
          <Space css={rightAlignCSS}>
            <Button type="primary" onClick={showModalBlackList}>
              블랙리스트 등록
            </Button>
            <Modal
              title="블랙리스트 등록"
              open={modalBlackList}
              onOk={handleOkBlackList}
              onCancel={handleCancelBlackList}
              okText="등록"
              cancelText="취소"
            >
              <p>블랙리스트로 등록된 회원은 ...</p>
              <Space css={rightAlignCSS}>총 명</Space>
              <Table
                //style={{ width: 900 }}
                columns={columnsBlackList}
                dataSource={dataBlackList}
              />
            </Modal>

            <Button type="primary" onClick={showModalDelUser}>
              삭제
            </Button>
            <Modal
              title="회원 삭제"
              open={modalDelUser}
              onOk={handleOkDelUser}
              onCancel={handleCancelDelUser}
              okText="삭제"
              cancelText="취소"
            >
              <p>회원 삭제 시...</p>
              <Space css={rightAlignCSS}>총 명</Space>
              <Table
                //style={{ width: 900 }}
                columns={columnsDelUser}
                dataSource={dataDelUser}
              />
            </Modal>
          </Space>
          <Space css={rightAlignCSS}>
            {hasSelected
              ? `${selectedRowKeys.length}/${data.length}개 선택됨`
              : `${selectedRowKeys.length}/${data.length}개 선택됨`}
          </Space>
          <Space>
            <Table
              css={{ width: 1570 }}
              rowSelection={rowSelection}
              columns={columns}
              dataSource={data}
            />
          </Space>
        </Card>
      </Space>
      <Modal
        title="프로필 사진"
        open={modalProfileImage}
        onOk={handleOkProfileImage}
        onCancel={handleCancelProfileImage}
        okText="확인"
        cancelText="취소"
      >
        <Image
          width={200}
          src="https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png"
        />
      </Modal>

      <Modal
        title="활동 목록"
        open={modalDetail}
        onOk={handleOkDetail}
        onCancel={handleCancelDetail}
        okText="확인"
        cancelText="취소"
        width=""
      >
        <Card
          tabList={tabList}
          activeTabKey={activeTabKey}
          onTabChange={onTabChange}
        >
          {contentList[activeTabKey]}
        </Card>
      </Modal>

      <Modal
        title="회원 정보 수정"
        open={modalEdit}
        onOk={handleOkEdit}
        onCancel={handleCancelEdit}
        okText="수정"
        cancelText="취소"
      ></Modal>
    </div>
  );
};

export default UserManagementPage;

import { useCallback, useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import { useSetRecoilState } from 'recoil';

import { headerTitleState } from '../../states/uiState';
import { PATH } from '../../utils/paths';
import './My.css';
import { apiRequest } from '../../api/request';
import { API_URL } from '../../api/urls';

const MyPage = () => {
  const setHeaderTitle = useSetRecoilState(headerTitleState);

  useEffect(() => {
    setHeaderTitle('내정보');
  }, [setHeaderTitle]);

  useEffect(() => {
    apiRequest
      .get(API_URL.USER_INFO)
      .then((res) => {
        console.log(res);

        setImage(
          res.data.image_url ||
            'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png',
        );
        setNickName(res.data.nick_name);
        setTown(res.data.location);
        setIntroduction(res.data.introduction);
        setPersonalNum(res.data.id);
        setTimePay(res.data.time_pay);
      })
      .catch((error) => {
        console.error('Error sending GET request:', error);
      });
  }, []);

  const [image, setImage]: any = useState();
  const [nickName, setNickName]: any = useState();
  const [town, setTown]: any = useState();
  const [introduction, setIntroduction]: any = useState();
  const [personalNum, setPersonalNum]: any = useState();
  const [timePay, setTimePay]: any = useState();

  const navigate = useNavigate();

  const handlePageMove = useCallback(
    (path: string) => {
      navigate(path);
    },
    [navigate],
  );

  return (
    <div className="MyPage">
      <div className="MyContentWrap">
        <div className="MyEdit">
          <button className="Edit" onClick={() => handlePageMove(PATH.MY_EDIT)}>
            프로필 수정
          </button>
        </div>

        <div className="MyBlock">
          {' '}
          <div className="MyTopBox">
            <div className="MyImageWrap">
              <img src={image} className="MyProfileImage" alt="내 프로필" />
            </div>
            <div className="space"></div>

            <div className="MyNameWrap">
              <div className="MyName">{nickName}</div>
              <div className="MyPersonalNum"> {'#' + personalNum}</div>
            </div>
          </div>
        </div>

        <div className="MyBlock">
          <div className="MyTotalBox">
            <div className="MyLeftBox">
              <div className="MyLeft">
                <div className="MyTitleText">나의 타임페이</div>
              </div>
              <div className="MyLeft">
                <div className="MyTitleText">나의 동네</div>
              </div>
              <div className="MyLeft">
                <div className="MyTitleText">나의 소개</div>
              </div>
            </div>

            <div className="MyRightBox">
              <div className="MyRight">
                <div className="MyContentText">{timePay}</div>
              </div>
              <div className="MyRight">
                <div className="MyContentText">{town}</div>
              </div>
              <div className="MyRight">
                <div className="MyContentText">{introduction}</div>
              </div>
            </div>
          </div>
        </div>

        <div className="MyBlock">
          <div className="MyBlockBox">
            <div className="MyPageMoveBox">
              <button
                className="MyPageText"
                onClick={() => handlePageMove(PATH.MY_ACTIVITY_RECORD)}
              >
                활동 기록
              </button>
            </div>

            <div className="MyPageMoveBox">
              <button
                className="MyPageText"
                onClick={() => handlePageMove(PATH.REPORT)}
              >
                신고 기록
              </button>
            </div>

            <div className="MyPageMoveBox">
              <button
                className="MyPageText"
                onClick={() => handlePageMove(PATH.INQUIRE)}
              >
                문의하기
              </button>
            </div>

            <div className="MyPageMoveBox">
              <button
                className="MyPageText"
                onClick={() => handlePageMove(PATH.CATEGORY_SELECT)}
              >
                카테고리 알림 설정
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyPage;

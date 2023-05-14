import { FloatButton } from 'antd';
import { useCallback, useEffect, useMemo } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { ReactComponent as BackArrow } from '../../assets/images/icons/header-back-arrow.svg';
import { cssMainHeaderStyle } from '../../components/MainHeader/MainHeader.styles';
import { EditFilled } from '@ant-design/icons';
import { useGetInquiry } from '../../api/hooks/inquiry';

import QnaList from '../../components/qna/QnaList';

const QnaListPage = () => {
  const { data, isLoading } = useGetInquiry();

  const inquiries = useMemo(() => {
    console.log(data?.data.content);
    return data?.data.content.map((qna) => ({
      ...qna,
    }));
  }, [data]);

  const navigate = useNavigate();
  const handleClickBack = useCallback(() => {
    navigate(-1);
  }, [navigate]);

  return (
    <div>
      <div css={cssMainHeaderStyle}>
        <BackArrow onClick={handleClickBack} />
        <span>문의하기</span>
      </div>
      <Link to="/inquire/register">
        <FloatButton
          shape="circle"
          type="primary"
          style={{ right: 24, width: 70, height: 70 }}
          icon={<EditFilled style={{ fontSize: 35, marginLeft: -8 }} />}
        />
      </Link>
      <div style={{ marginTop: 70 }}>
        {!isLoading &&
          inquiries &&
          inquiries.map((qna) => <QnaList key={qna.inquiryId} qna={qna} />)}
      </div>
    </div>
  );
};
export default QnaListPage;

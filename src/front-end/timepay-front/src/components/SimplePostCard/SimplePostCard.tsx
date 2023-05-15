import { Card, Spin } from 'antd';
import { useCallback, useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { IBoard } from '../../api/interfaces/IPost';
import PostStatusTag from '../PostStatusTag';
import {
  cssSimplePostCardBodyStyle,
  cssSimplePostCardFooterStyle,
  cssSimplePostCardHeadStyle,
  cssSimplePostCardStyle,
} from './SimplePostCard.styles';
import { ReactComponent as RegionPin } from '../../assets/images/icons/region-pin.svg';
import { ReactComponent as Clock } from '../../assets/images/icons/clock.svg';
import { ReactComponent as Attachment } from '../../assets/images/icons/attachment.svg';
import { UserOutlined } from '@ant-design/icons';
import { ReactComponent as PostDetailArrow } from '../../assets/images/icons/post-detail-arrow.svg';
import PostTypeTag from '../PostTypeTag';
import { cssPostTypeTagStyle } from '../PostTypeTag/PostTypeTag.styles';
import { COMMON_COLOR } from '../../styles/constants/colors';

interface SimplePostCardProps {
  post?: IBoard;
}

const SimplePostCard = ({ post }: SimplePostCardProps) => {
  const navigate = useNavigate();
  const handlePageChange = () => {
    navigate(`/post/${post?.d_boardId}`, {
      state: {
        id: post?.d_boardId,
        type: post?.type,
        title: post?.title,
        content: post?.content,
        createdAt: post?.createdAt,
        status: post?.boardStatus,
        category: post?.category,
        pay: post?.pay,
        startTime: post?.startTime,
        endTime: post?.endTime,
        region: post?.location,
        // attachment: post?.attachment,
        // user: post?.user?.name,
      },
    });
  };
  const footerComponent = useCallback(
    (nickname?: string, createdAt?: string) => {
      return (
        <div css={cssSimplePostCardFooterStyle}>
          <div className="nickname">
            <UserOutlined />
            {nickname || '-'}
          </div>
          <div className="view-more-container">
            <span className="view-more-text">더보기</span>
            <span className="view-more-icon">
              <PostDetailArrow />
            </span>
          </div>
          <div>{createdAt || '-'}</div>
        </div>
      );
    },
    [],
  );

  const postCardContent = useMemo(() => {
    return (
      <div>
        <div css={cssSimplePostCardHeadStyle}>
          <div className="tag">
            <div className="type">
              <PostTypeTag type={post?.type} />
            </div>
            <div
              className="amount"
              css={cssPostTypeTagStyle({
                backgroundColor: COMMON_COLOR.MAIN1,
              })}
            >
              {post?.pay || '-'} TP
            </div>
          </div>
          <div className="title">
            <PostStatusTag status={post?.boardStatus} />
            <div>{post?.title || '-'}</div>
            <div className="attachment">{post?.imageUrl && <Attachment />}</div>
          </div>
        </div>
        <div css={cssSimplePostCardBodyStyle}>
          <div className="post-card-location">
            <RegionPin />
            {post?.location || '-'}
          </div>
          <div className="post-card-time">
            <Clock />
            {post ? (
              <span>
                {post.startTime?.split('.')[0].replace('T', ' ')} ~
                {post.endTime?.split('.')[0].replace('T', ' ')}
              </span>
            ) : (
              <span>-</span>
            )}
          </div>
          <div className="post-card-content">
            {post?.content || '로딩 중...'}
          </div>
        </div>
      </div>
    );
  }, [post]);

  return (
    <Card
      bordered={false}
      css={cssSimplePostCardStyle}
      onClick={handlePageChange}
    >
      <Spin size="large" spinning={!post}>
        {postCardContent}
      </Spin>
      {footerComponent(post?.writerNickname, post?.createdAt)}
    </Card>
  );
};

export default SimplePostCard;

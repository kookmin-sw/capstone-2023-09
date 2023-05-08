import { Layout } from 'antd';
import { useMemo } from 'react';
import { Outlet, useLocation } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { headerTitleState } from '../../states/uiState';
import { PATH } from '../../utils/paths';
import HomeHeader from '../HomeHeader';
import MainFooter from '../MainFooter';
import MainHeader from '../MainHeader';
import SearchHeader from '../SearchHeader';
import { cssBaseLayoutStyle } from './BaseLayout.styles';

const BaseLayout = () => {
  const headerTitle = useRecoilValue(headerTitleState);
  const location = useLocation();

  const isHome = useMemo(() => {
    return location.pathname === PATH.HOME;
  }, [location]);

  const isnoFooter =
    location.pathname === PATH.Register_F ||
    location.pathname === PATH.Register_HR ||
    location.pathname === PATH.Register_HS ||
    location.pathname === PATH.Qna_List ||
    location.pathname === PATH.Qna_Register ||
    location.pathname.includes('/post/') ||
    location.pathname.includes('/edit/');

  const isSearch = useMemo(() => {
    return location.pathname === PATH.SEARCH;
  }, [location]);

  const Header = useMemo(() => {
    if (isHome) return <HomeHeader />;
    if (isSearch) return <SearchHeader />;
    if (headerTitle) return <MainHeader />;
  }, [isHome, isSearch, headerTitle]);

  return (
    <Layout css={cssBaseLayoutStyle}>
      {Header}
      <Layout.Content
        className={`main-section-container ${
          isSearch
            ? 'show-search-header'
            : isHome || headerTitle
            ? 'show-header'
            : 'no-header'
        }`}
      >
        <Outlet />
      </Layout.Content>
      {isnoFooter ? null : <MainFooter />}
    </Layout>
  );
};
export default BaseLayout;

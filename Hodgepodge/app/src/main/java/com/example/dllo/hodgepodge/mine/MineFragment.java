package com.example.dllo.hodgepodge.mine;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.mine.designer.DesignerActivity;
import com.example.dllo.hodgepodge.mine.msgcenter.MsgCenterActivity;
import com.example.dllo.hodgepodge.mine.mypictorial.PictorialActivity;
import com.example.dllo.hodgepodge.mine.suggest.SuggestActivity;
import com.example.dllo.hodgepodge.mine.wish.WishActivity;

/**
 * Created by dllo on 16/12/19.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener{

    private ImageView mineSet, loginImage;
    private TextView mineName;
    private RelativeLayout minePictorial,attentionDesigner,mineWishList,messageCenter,mineSuggest, beautifulApp;
    private Intent mIntent;
    private PopupWindow mPopupWindow;
    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
       findId();
    }

    // 找ID
    private void findId() {
        mineSet = bindView(R.id.mine_set);
        mineName = bindView(R.id.mine_name);
        loginImage = bindView(R.id.login_image);
        minePictorial = bindView(R.id.mine_pictorial);
        attentionDesigner = bindView(R.id.attention_designer);
        mineWishList = bindView(R.id.mine_wish_list);
        messageCenter = bindView(R.id.message_center);
        mineSuggest = bindView(R.id.mine_suggest);
        beautifulApp = bindView(R.id.beautiful_app);
        setClick(this, mineSet,loginImage, minePictorial,
                attentionDesigner, mineWishList,
                messageCenter, mineSuggest,beautifulApp);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_set:
                mIntent = new Intent(getActivity(), SetActivity.class);
                startActivity(mIntent);
                break;
            case R.id.login_image:
                if (mPopupWindow == null || !mPopupWindow.isShowing())  {
                    popShow();
                }else {
                    mPopupWindow.dismiss();
                }

                break;
            case R.id.mine_pictorial:
                mIntent = new Intent(getActivity(), PictorialActivity.class);
                startActivity(mIntent);
                break;
            case R.id.attention_designer:
                mIntent = new Intent(getActivity(), DesignerActivity.class);
                startActivity(mIntent);
                break;
            case R.id.mine_wish_list:
                mIntent = new Intent(getActivity(), WishActivity.class);
                startActivity(mIntent);
                break;
            case R.id.message_center:
                mIntent = new Intent(getActivity(), MsgCenterActivity.class);
                startActivity(mIntent);
                break;
            case R.id.mine_suggest:
                mIntent = new Intent(getActivity(), SuggestActivity.class);
                startActivity(mIntent);
                break;
            case R.id.beautiful_app:

                break;

        }

    }

    private void popShow() {
        mPopupWindow = new PopupWindow(getView(), 700, 900, true);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_up,null);
        mPopupWindow.setContentView(view);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);
    }

}

package project.mobile.mnemosyne;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BackCardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BackCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BackCardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BackCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BackCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BackCardFragment newInstance(String param1, String param2) {
        BackCardFragment fragment = new BackCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);

        //Change camera distance, so the animation can be shown in full
        float scale = getActivity().getResources().getDisplayMetrics().density;
        rootView.setCameraDistance(8000 * scale);

        //Get current deck
        final Deck currentDeck = (Deck) getActivity().getIntent().getSerializableExtra("deck");
        currentDeck.toString();

        //Set card text fields and picture
        //Get current card
        Card currentCard = currentDeck.getCurrentCard();

        //Text
        TextView cardText = rootView.findViewById(R.id.cardText);
        cardText.setText(currentCard.getBtext());

        //Picture
        ImageView cardImage = rootView.findViewById(R.id.cardImage);

        //Remove image if null
        if (currentCard.getBmedia() != null)
            cardImage.setImageResource(getResources().getIdentifier(currentCard.getBmedia(), "drawable", getContext().getPackageName()));
        else
            cardImage.getLayoutParams().height = 0;

        //Fade the card if is rated
        if (currentCard.isKnown() != null) {
            CardView cardView = rootView.findViewById(R.id.cardFront);
            cardView.setAlpha(0.4f);
        }

        //Gesture handle
        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            //Swipe from right to left
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                //Check if next card is available
                                if (!currentDeck.gotoNextCard())
                                    return false;

                                //Play sound
                                MediaPlayer mPlayer = MediaPlayer.create(getActivity(), R.raw.swipe);
                                mPlayer.start();

                                //Add previous card fragment
                                FrontCardFragment frontCardFragment = new FrontCardFragment();

                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                                transaction.replace(R.id.frameLayout, frontCardFragment);
                                transaction.commit();
                                //Swipe from left to right
                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                //Check if next card is available
                                if (!currentDeck.gotoPreviousCard())
                                    return false;

                                //Play sound
                                MediaPlayer mPlayer = MediaPlayer.create(getActivity(), R.raw.swipe);
                                mPlayer.start();

                                //Add next card fragment
                                FrontCardFragment frontCardFragment = new FrontCardFragment();

                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                                transaction.replace(R.id.frameLayout, frontCardFragment);
                                transaction.commit();
                            }
                        } catch (Exception e) {

                        }

                        return super.onFling(e1, e2, velocityX, velocityY);
                    }

                    ;

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        //Play sound
                        MediaPlayer mPlayer = MediaPlayer.create(getActivity(), R.raw.flip);
                        mPlayer.start();

                        //Flip the card
                        FrontCardFragment frontCardFragment = new FrontCardFragment();

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.in_animation, R.anim.out_animation);
                        transaction.replace(R.id.frameLayout, frontCardFragment);
                        transaction.commit();

                        return true;
                    }
                });

        rootView.setOnClickListener(null);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

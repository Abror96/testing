package com.era.a100;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;

public class SchemeClass extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private ArrayList<Rectangle> rack_objects;
    private Rectangle checking_touch_rectangle;

    private static OnClickListener onClickListener;

    public static void setOnClickListener(OnClickListener onClickListener) {
        SchemeClass.onClickListener = onClickListener;
    }

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        checking_touch_rectangle = new Rectangle(0, 0, 1, 1);

        rack_objects = new ArrayList<Rectangle>();
        rack_objects.add(new Rectangle(30, 30, 100, 100));
        rack_objects.add(new Rectangle(30, 135, 100, 100));
        rack_objects.add(new Rectangle(30, 240, 100, 100));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (int i = 0; i < rack_objects.size(); i++) {
            float x = rack_objects.get(i).x, y = rack_objects.get(i).y, width = rack_objects.get(i).width,
                        height = rack_objects.get(i).height;
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.setColor(0.921f, 0.921f, 0.921f,0);
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.end();
        }
        batch.end();

        touching_checking();
    }

    private void touching_checking() {
        if (Gdx.input.isTouched()) {
            checking_touch_rectangle.setPosition(Gdx.input.getX(), Gdx.input.getY());
            for (int i = 0; i < rack_objects.size(); i++) {
                if (intersect(checking_touch_rectangle, rack_objects.get(i), checking_touch_rectangle)) {
                    onClickListener.onRackClicked(i+" удача");
                }
            }
        }
    }

    private static boolean intersect(Rectangle r1, Rectangle r2, Rectangle intersection) {
        if (!r1.overlaps(r2)) {
            return false;
        }

        float x = Math.max(r1.x, r2.x);
        float y = Math.max(r1.y, r2.y);
        float width = Math.min(r1.x + r1.width, r2.x + r2.width) - x;
        float height = Math.min(r1.y + r1.height, r2.y + r2.height) - y;
        intersection.set(x, y, width, height);

        return true;
    }
}

class CreateSubirExcels < ActiveRecord::Migration
  def change
    create_table :subir_excels do |t|
      t.string :name
      t.string :attachment

      t.timestamps null: false
    end
  end
end
